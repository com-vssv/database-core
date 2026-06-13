package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;

import java.io.IOException;

/**
 * Interface defining administrative and management operations for a cluster.
 * This includes managing partitions, locating tables, and persisting cluster metadata.
 *
 * @param <P> The specific type of {@link Partition} managed by the cluster.
 * @param <T> The specific type of {@link Table} managed by the cluster.
 */
public interface ClusterOperations<P extends Partition, T extends Table> {
    
    /**
     * Retrieves the partition where a specific table is located.
     *
     * @param tableName The name of the table to locate.
     * @return The partition containing the table, or null if not found.
     */
    P getPartition(String tableName);
    
    /**
     * Determines the appropriate partition for a newly created table.
     * This may involve creating a new partition or selecting an existing one based on the cluster's strategy.
     *
     * @param tableName The name of the new table.
     * @return The partition where the new table should be stored.
     */
    P getPartitionForNewTable(String tableName);
    
    /**
     * Retrieves the table object for a given table name.
     *
     * @param tableName The name of the table to retrieve.
     * @return The corresponding Table object, or null if not found.
     */
    T getTableFromTableName(String tableName);
    
    /**
     * Updates the number of partitions managed by the cluster.
     * This may involve redistributing data or creating new storage structures.
     *
     * @param newNoOfPartitions The new desired number of partitions.
     * @throws IOException If an I/O error occurs while updating the cluster structure.
     */
    void setNoOfPartitions(int newNoOfPartitions) throws IOException;
    
    /**
     * Persists the cluster's metadata and configuration to storage.
     *
     * @throws IOException If an I/O error occurs during persistence.
     */
    void writeCluster() throws IOException;
}