package com.vssv.database.core.models;

/**
 * Represents a table whose data is stored within a single file or a specific file-based partition.
 * It tracks its location within the file structure.
 */
public class FileTypeTable extends Table {
    
    /**
     * The specific file location index or identifier for this table.
     * Defaults to -1 indicating unassigned.
     */
    public int fileLocation = -1;

    /**
     * Constructs a new FileTypeTable.
     *
     * @param partitionIdentifier The ID of the partition containing this table.
     * @param tableName The name of the table.
     */
    public FileTypeTable(Integer partitionIdentifier, String tableName) {
        super(partitionIdentifier, tableName);
    }
}