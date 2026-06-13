package com.vssv.database.core.models;

import java.io.File;

/**
 * Represents a partition where data is organized into folders.
 * Each partition maps to a specific directory on the filesystem.
 */
public class FolderTypePartition extends Partition {
    
    /**
     * The absolute or relative path to the partition's directory.
     */
    public final String directoryPath;
    
    /**
     * The File object representing the partition's directory.
     */
    public final File directory;

    /**
     * Constructs a new FolderTypePartition and creates the underlying directory if it doesn't exist.
     *
     * @param partitionID The unique identifier for this partition.
     * @param basePath The base directory where the partition folder should be located.
     * @throws IllegalArgumentException If the directory cannot be created.
     */
    public FolderTypePartition(int partitionID, String basePath) {
        super(PartitionType.Folder, partitionID);
        directoryPath = basePath + "/" + partitionID;
        directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (!result) {
                throw new IllegalArgumentException("The given path does not exists.");
            }
        }
    }
}