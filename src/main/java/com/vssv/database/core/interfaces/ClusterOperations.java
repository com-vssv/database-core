package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;

import java.io.IOException;

public interface ClusterOperations<P extends Partition, T extends Table> {
    P getPartition(String tableName);
    P getPartitionForNewTable(String tableName);
    T getTableFromTableName(String tableName);
    void setNoOfPartitions(int newNoOfPartitions) throws IOException;
    void writeCluster() throws IOException;
}
