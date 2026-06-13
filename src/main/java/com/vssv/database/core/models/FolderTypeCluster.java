package com.vssv.database.core.models;

import java.util.List;

/**
 * Represents a cluster specifically designed to manage {@link FolderTypeTable} instances.
 * In a FolderTypeCluster, tables within a partition are typically organized into separate folders.
 */
public class FolderTypeCluster extends Cluster<FolderTypeTable> {
    
    /**
     * Constructs a new FolderTypeCluster.
     *
     * @param basePath The base directory path where the cluster data is stored.
     * @param tables The list of {@link FolderTypeTable} instances managed by this cluster.
     */
    public FolderTypeCluster(String basePath, List<FolderTypeTable> tables) {
        super(basePath, tables);
    }
}