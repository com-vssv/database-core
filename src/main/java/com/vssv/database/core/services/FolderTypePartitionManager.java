package com.vssv.database.core.services;

import com.vssv.database.core.models.FolderTypePartition;
import com.vssv.database.core.models.PartitionType;

import java.io.IOException;

/**
 * A specialized {@link PartitionManager} that handles partitions stored as individual folders.
 */
public class FolderTypePartitionManager extends PartitionManager<FolderTypePartition> {
    
    /**
     * Constructs a new FolderTypePartitionManager.
     *
     * @param basePath The base directory where partition folders should be created.
     */
    public FolderTypePartitionManager(String basePath) {
        super(basePath, PartitionType.Folder);
    }

    /**
     * Initializes a new {@link FolderTypePartition}. This will physically create the underlying
     * directory on the filesystem if it doesn't exist.
     *
     * @param identifier The unique identifier for the partition.
     * @return The newly initialized partition.
     * @throws IOException If an error occurs during directory creation.
     */
    @Override
    protected FolderTypePartition initializePartition(int identifier) throws IOException {
        return new FolderTypePartition(identifier, basePath);
    }
}