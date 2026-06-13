package com.vssv.database.core.models;

/**
 * Represents a table where data is stored in a dedicated folder structure.
 * Each table is mapped to a directory within its partition.
 */
public class FolderTypeTable extends Table {

    /**
     * Constructs a new FolderTypeTable.
     *
     * @param partitionIdentifier The ID of the partition containing this table.
     * @param tableName The name of the table.
     */
    public FolderTypeTable(Integer partitionIdentifier, String tableName) {
        super(partitionIdentifier, tableName);
    }
}