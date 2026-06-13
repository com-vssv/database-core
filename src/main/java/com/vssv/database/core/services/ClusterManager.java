package com.vssv.database.core.services;

import com.vssv.database.core.interfaces.ClusterOperations;
import com.vssv.database.core.models.Cluster;
import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class ClusterManager<T extends Table, C extends Cluster<T>, P extends Partition, PM extends PartitionManager<P>> implements ClusterOperations<P, T> {
    public final String basePath;
    protected final C cluster;
    private final Map<T, Integer> tablePartitionMap = new TreeMap<>();
    private final PM partitionManager;
    private static final String CLUSTER_CONFIG_FILE_NAME = "clusterConfig.ser";

    protected ClusterManager(String basePath, PM partitionManager) throws IOException, ClassNotFoundException {
        this.basePath = basePath;
        this.partitionManager = partitionManager;

        try (FileInputStream fileIn = new FileInputStream(basePath + "/" + CLUSTER_CONFIG_FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            cluster = (C) objectIn.readObject();
        }
        synchronized (cluster) {
            cluster.tables.forEach(table -> tablePartitionMap.put(table, table.partitionIdentifier));
        }
    }

    public P getPartition(String tableName){
        Integer partitionIdentifier = tablePartitionMap.get(tableName);
        if (partitionIdentifier == null){
            throw new IllegalArgumentException("Table with the table name does not exists.");
        }
        return partitionManager.getPartition(partitionIdentifier);
    }

    public P getPartitionForNewTable(String tableName) {
        Integer partitionIdentifier = tablePartitionMap.get(tableName);
        if (partitionIdentifier != null){
            throw new IllegalArgumentException("Table with the table name already exists.");
        }
        T table = createTable(tableName);
        tablePartitionMap.put(table, table.partitionIdentifier);
        cluster.tables.add(table);
        partitionIdentifier = table.partitionIdentifier;
        return partitionManager.getPartition(partitionIdentifier);
    }

    @Override
    public T getTableFromTableName(String tableName) {
        T table = cluster.tables.stream().filter(t -> t.tableName.equals(tableName)).findFirst().orElse(null);
        if (table == null){
            throw new IllegalArgumentException("Table with the table name does not exists.");
        }
        return table;
    }

    @Override
    public void setNoOfPartitions(int newNoOfPartitions) throws IOException {
        cluster.setNoOfPartitions(newNoOfPartitions);
        partitionManager.setNoOfPartitions(newNoOfPartitions);
    }

    protected abstract T createTable(String tableName);

    public void writeCluster() throws IOException {
        synchronized (cluster) {
            try (FileOutputStream fileOut = new FileOutputStream(basePath + "/" + CLUSTER_CONFIG_FILE_NAME);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(cluster);
            }
        }
    }
}
