package com.vssv.database.core.services;

import com.vssv.database.core.models.FolderTypeCluster;
import com.vssv.database.core.models.FolderTypePartition;
import com.vssv.database.core.models.FolderTypeTable;

import java.io.IOException;

public class FolderTypeClusterManager extends ClusterManager<FolderTypeTable, FolderTypeCluster, FolderTypePartition, FolderTypePartitionManager> {
    protected FolderTypeClusterManager(String basePath, FolderTypePartitionManager partitionManager) throws IOException, ClassNotFoundException {
        super(basePath, partitionManager);
    }

    @Override
    protected FolderTypeTable createTable(String tableName) {
        return new FolderTypeTable(tableName.hashCode() % cluster.noOfPartitions, tableName);
    }
}
