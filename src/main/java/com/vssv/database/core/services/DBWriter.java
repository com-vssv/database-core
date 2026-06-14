package com.vssv.database.core.services;

import com.vssv.database.core.interfaces.ClusterOperations;
import com.vssv.database.core.interfaces.WriteOperations;
import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;
import com.vssv.database.core.models.TableSchema;
import com.vssv.database.core.models.WriteRecord;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * Abstract base class representing a service responsible for writing data to the database.
 * It coordinates with {@link ClusterOperations} to find the appropriate partitions and tables,
 * and delegates the actual writing to subclass implementations.
 *
 * @param <TS> The type of {@link TableSchema} used by the records.
 * @param <W>  The type of {@link WriteRecord} handled by this writer.
 * @param <P>  The type of {@link Partition} where data is written.
 * @param <T>  The type of {@link Table} to which data belongs.
 * @param <CO> The type of {@link ClusterOperations} used for cluster management.
 */
@RequiredArgsConstructor
public abstract class DBWriter<TS extends TableSchema, W extends WriteRecord<TS>, P extends Partition, T extends Table, CO extends ClusterOperations<P, T>> implements WriteOperations<W, P, T> {
    
    /**
     * The cluster operations service used to interact with the database cluster.
     */
    protected final CO clusterOperations;

    /**
     * {@inheritDoc}
     * Internally, this delegates the creation logic to {@link #writeToPartition(Partition, Table)}
     * and saves the cluster state upon success.
     */
    @Override
    public boolean createTable(String tableName) throws IOException {
        P partition = clusterOperations.getPartitionForNewTable(tableName);
        T table = clusterOperations.getTableFromTableName(tableName);
        boolean result = writeToPartition(partition, table);
        if (!result)
            return false;
        clusterOperations.writeCluster();
        return true;
    }

    /**
     * {@inheritDoc}
     * Internally, this finds the appropriate partition and table, delegates the persistence logic
     * to {@link #writeToPartition(Partition, Table, WriteRecord)}, and saves the cluster state upon success.
     */
    @Override
    public boolean persistRecord(W writeRecord) throws IOException {
        P partition = clusterOperations.getPartition(writeRecord.tableName);
        T table = clusterOperations.getTableFromTableName(writeRecord.tableName);
        boolean result = writeToPartition(partition, table, writeRecord);
        if (!result)
            return false;
        clusterOperations.writeCluster();
        return true;
    }

    /**
     * Performs the physical write operation to initialize a new table within a partition.
     * Must be implemented by subclasses to handle the specific storage format.
     *
     * @param partition The partition where the table should be created.
     * @param table     The table metadata.
     * @return True if the physical creation was successful, false otherwise.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    protected abstract boolean writeToPartition(P partition, T table) throws IOException;
    
    /**
     * Performs the physical write operation to persist a record within a partition.
     * Must be implemented by subclasses to handle the specific storage format.
     *
     * @param partition   The partition where the record should be written.
     * @param table       The table to which the record belongs.
     * @param writeRecord The record data to be written.
     * @return True if the physical write was successful, false otherwise.
     * @throws IOException If an I/O error occurs during the write operation.
     */
    protected abstract boolean writeToPartition(P partition, T table, W writeRecord) throws IOException;
}