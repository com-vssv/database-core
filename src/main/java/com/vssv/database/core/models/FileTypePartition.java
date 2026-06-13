package com.vssv.database.core.models;

import java.io.File;
import java.io.IOException;

/**
 * Represents a partition where all table data is stored within a single file.
 * It ensures the underlying file exists upon creation.
 */
public class FileTypePartition extends Partition {
    
    /**
     * The absolute or relative path to the partition's file.
     */
    public final String filePath;
    
    /**
     * The File object representing the partition's data file.
     */
    public final File file;

    /**
     * Constructs a new FileTypePartition and creates the underlying file if it doesn't exist.
     *
     * @param partitionID The unique identifier for this partition.
     * @param basePath The base directory where the file should be located.
     * @param fileExtension The extension for the partition file.
     * @throws IOException If an I/O error occurs during file creation.
     * @throws IllegalArgumentException If the file cannot be created.
     */
    public FileTypePartition(int partitionID, String basePath, String fileExtension) throws IOException {
        super(PartitionType.File, partitionID);
        filePath = basePath + "/" + partitionID + "." + fileExtension;
        file = new File(filePath);
        if (!file.exists()) {
            boolean result = file.createNewFile();
            if (!result) {
                throw new IllegalArgumentException("The given path does not exists.");
            }
        }
    }
}