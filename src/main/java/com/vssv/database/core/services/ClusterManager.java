package com.vssv.database.core.services;

import com.vssv.database.core.interfaces.ClusterOperations;
import com.vssv.database.core.models.Cluster;
import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for managing a database cluster.
 * It handles the lifecycle of a cluster, including loading its configuration, managing tables
 * and partitions, and persisting its state.
 *
 * @param <T>  The type of {@link Table} managed by this cluster.
 * @param <C>  The type of {@link Cluster} being managed.
 * @param <P>  The type of {@link Partition} used within the cluster.
 * @param <PM> The type of {@link PartitionManager} used to manage partitions.
 */
public abstract class ClusterManager<T extends Table, C extends Cluster<T>, P extends Partition, PM extends PartitionManager<P>> implements ClusterOperations<P, T> {
    
    /**
     * The base file path for the cluster's storage.
     */
    public final String basePath;
    
    /**
     * The cluster instance being managed.
     */
    protected final C cluster;
    
    /**
     * A map to quickly look up the partition identifier for a given table.
     */
    private final Map<T, Integer> tablePartitionMap = new TreeMap<>();
    
    /**
     * The manager responsible for handling the cluster's partitions.
     */
    private final PM partitionManager;
    
    /**
     * The name of the serialized file where the cluster's configuration is stored.
     */
    private static final String CLUSTER_CONFIG_FILE_NAME = "clusterConfig.ser";

    /**
     * Constructs a ClusterManager, loading an existing cluster configuration from disk.
     *
     * @param basePath         The base path where the cluster data is stored.
     * @param partitionManager The manager for the partitions within this cluster.
     * @throws IOException            If an I/O error occurs while reading the cluster configuration.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
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

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if a table with the given name does not exist.
     */
    public P getPartition(String tableName) {
        Integer partitionIdentifier = tablePartitionMap.get(tableName);
        if (partitionIdentifier == null) {
            throw new IllegalArgumentException("Table with the table name does not exists.");
        }
        return partitionManager.getPartition(partitionIdentifier);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if a table with the given name already exists.
     */
    public P getPartitionForNewTable(String tableName) {
        Integer partitionIdentifier = tablePartitionMap.get(tableName);
        if (partitionIdentifier != null) {
            throw new IllegalArgumentException("Table with the table name already exists.");
        }
        T table = createTable(tableName);
        tablePartitionMap.put(table, table.partitionIdentifier);
        cluster.tables.add(table);
        partitionIdentifier = table.partitionIdentifier;
        return partitionManager.getPartition(partitionIdentifier);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if a table with the given name does not exist.
     */
    @Override
    public T getTableFromTableName(String tableName) {
        T table = cluster.tables.stream().filter(t -> t.tableName.equals(tableName)).findFirst().orElse(null);
        if (table == null) {
            throw new IllegalArgumentException("Table with the table name does not exists.");
        }
        return table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNoOfPartitions(int newNoOfPartitions) throws IOException {
        cluster.setNoOfPartitions(newNoOfPartitions);
        partitionManager.setNoOfPartitions(newNoOfPartitions);
    }

    /**
     * Creates a new table instance. This method must be implemented by subclasses to
     * provide the specific table type for the cluster.
     *
     * @param tableName The name of the table to create.
     * @return The newly created table instance.
     */
    protected abstract T createTable(String tableName);

    /**
     * {@inheritDoc}
     */
    public void writeCluster() throws IOException {
        synchronized (cluster) {
            try (FileOutputStream fileOut = new FileOutputStream(basePath + "/" + CLUSTER_CONFIG_FILE_NAME);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(cluster);
            }
        }
    }
}