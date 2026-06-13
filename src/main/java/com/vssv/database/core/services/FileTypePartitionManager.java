package com.vssv.database.core.services;

import com.vssv.database.core.models.FileTypePartition;
import com.vssv.database.core.models.PartitionType;

import java.io.IOException;

/**
 * A specialized {@link PartitionManager} that handles partitions stored as individual files.
 */
public class FileTypePartitionManager extends PartitionManager<FileTypePartition> {
    
    /**
     * The file extension to use for the partition files.
     */
    public final String fileExtension;
    
    /**
     * Constructs a new FileTypePartitionManager.
     *
     * @param basePath The base directory where partition files should be created.
     * @param fileExtension The extension for the partition files (e.g., "dat", "db").
     */
    public FileTypePartitionManager(String basePath, String fileExtension) {
        super(basePath, PartitionType.File);
        this.fileExtension = fileExtension;
    }

    /**
     * Initializes a new {@link FileTypePartition}. This will physically create the underlying
     * file on the filesystem if it doesn't exist.
     *
     * @param identifier The unique identifier for the partition.
     * @return The newly initialized partition.
     * @throws IOException If an error occurs during file creation.
     */
    @Override
    protected FileTypePartition initializePartition(int identifier) throws IOException {
        return new FileTypePartition(identifier, basePath, fileExtension);
    }
}