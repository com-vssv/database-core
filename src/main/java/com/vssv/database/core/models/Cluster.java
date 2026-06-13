package com.vssv.database.core.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a cluster within the database, which groups a collection of tables 
 * and partitions them for storage.
 *
 * @param <T> The specific type of Table stored in this cluster.
 */
@Data
public abstract class Cluster<T extends Table> implements Serializable {
    
    /**
     * The base path where the cluster's data is stored on disk.
     */
    public final String basePath;
    
    /**
     * The list of tables contained within this cluster.
     */
    public final List<T> tables;
    
    /**
     * The number of partitions currently configured for this cluster.
     */
    public int noOfPartitions;
}