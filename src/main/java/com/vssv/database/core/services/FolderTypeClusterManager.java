package com.vssv.database.core.services;

import com.vssv.database.core.models.FolderTypeCluster;
import com.vssv.database.core.models.FolderTypePartition;
import com.vssv.database.core.models.FolderTypeTable;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A specialized {@link ClusterManager} for handling clusters where data is stored in separate folders per partition.
 * It uses {@link FolderTypePartitionManager} to handle the physical directories and creates {@link FolderTypeTable} instances.
 */
public class FolderTypeClusterManager extends ClusterManager<FolderTypeTable, FolderTypeCluster, FolderTypePartition, FolderTypePartitionManager> {
    
    /**
     * Constructs a new FolderTypeClusterManager. Attempts to read an existing cluster configuration from disk.
     * If it fails (e.g., file not found), it initializes a new cluster with the specified number of partitions.
     *
     * @param basePath The base directory where the cluster data is stored.
     * @param partitionManager The manager responsible for handling the folder-based partitions.
     * @throws ClassNotFoundException If the serialized cluster configuration is invalid.
     */
    protected FolderTypeClusterManager(String basePath, FolderTypePartitionManager partitionManager) throws ClassNotFoundException {
        super(basePath, partitionManager);
        try {
            readCluster();
        } catch (IOException ioException) {
            cluster = new FolderTypeCluster(basePath, new ArrayList<>());
        }
    }

    /**
     * Creates a new {@link FolderTypeTable}. It assigns the table to a partition based on a simple
     * modulo operation on the table name's hash code.
     *
     * @param tableName The name of the table to create.
     * @return The newly created FolderTypeTable.
     */
    @Override
    protected FolderTypeTable createTable(String tableName) {
        return new FolderTypeTable(tableName.hashCode() % cluster.noOfPartitions, tableName);
    }
}