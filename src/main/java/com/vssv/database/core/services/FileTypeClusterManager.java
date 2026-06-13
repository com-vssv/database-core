package com.vssv.database.core.services;

import com.vssv.database.core.models.FileTypeCluster;
import com.vssv.database.core.models.FileTypePartition;
import com.vssv.database.core.models.FileTypeTable;

import java.io.IOException;

public class FileTypeClusterManager extends ClusterManager<FileTypeTable, FileTypeCluster, FileTypePartition, FileTypePartitionManager> {
    protected FileTypeClusterManager(String basePath, FileTypePartitionManager partitionManager) throws IOException, ClassNotFoundException {
        super(basePath, partitionManager);
    }

    @Override
    protected FileTypeTable createTable(String tableName) {
        return new FileTypeTable(tableName.hashCode() % cluster.noOfPartitions, tableName);
    }
}
