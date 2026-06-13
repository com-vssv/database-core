package com.vssv.database.core.models;

/**
 * Defines the types of partitions available in the database.
 * Partitions can be organized by folders or individual files.
 */
public enum PartitionType {
    /**
     * A partition where each table is stored in its own folder.
     */
    Folder,
    /**
     * A partition where tables are stored together in a single file.
     */
    File
}