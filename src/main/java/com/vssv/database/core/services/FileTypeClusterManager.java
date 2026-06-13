package com.vssv.database.core.services;

import com.vssv.database.core.models.FileTypeCluster;
import com.vssv.database.core.models.FileTypePartition;
import com.vssv.database.core.models.FileTypeTable;

import java.io.IOException;

/**
 * A specialized {@link ClusterManager} for handling clusters where data is stored within individual files per partition.
 * It uses {@link FileTypePartitionManager} to handle the physical files and creates {@link FileTypeTable} instances.
 */
public class FileTypeClusterManager extends ClusterManager<FileTypeTable, FileTypeCluster, FileTypePartition, FileTypePartitionManager> {
    
    /**
     * Constructs a new FileTypeClusterManager.
     *
     * @param basePath The base directory where the cluster data is stored.
     * @param partitionManager The manager responsible for handling the file-based partitions.
     * @throws IOException If an I/O error occurs while loading the cluster.
     * @throws ClassNotFoundException If the serialized cluster configuration is invalid.
     */
    protected FileTypeClusterManager(String basePath, FileTypePartitionManager partitionManager) throws IOException, ClassNotFoundException {
        super(basePath, partitionManager);
    }

    /**
     * Creates a new {@link FileTypeTable}. It assigns the table to a partition based on a simple
     * modulo operation on the table name's hash code.
     *
     * @param tableName The name of the table to create.
     * @return The newly created FileTypeTable.
     */
    @Override
    protected FileTypeTable createTable(String tableName) {
        return new FileTypeTable(tableName.hashCode() % cluster.noOfPartitions, tableName);
    }
}