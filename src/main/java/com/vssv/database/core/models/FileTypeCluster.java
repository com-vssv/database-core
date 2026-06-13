package com.vssv.database.core.models;

import java.util.List;

/**
 * Represents a cluster specifically designed to manage {@link FileTypeTable} instances.
 * In a FileTypeCluster, all tables within a partition are typically stored within a single file.
 */
public class FileTypeCluster extends Cluster<FileTypeTable> {
    
    /**
     * Constructs a new FileTypeCluster.
     *
     * @param basePath The base directory path where the cluster data is stored.
     * @param tables The list of {@link FileTypeTable} instances managed by this cluster.
     */
    public FileTypeCluster(String basePath, List<FileTypeTable> tables) {
        super(basePath, tables);
    }
}