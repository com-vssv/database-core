package com.vssv.database.core.models;

/**
 * Defines the types of write operations that can be performed on the database.
 */
public enum WriteOperationType {
    /**
     * Operation to create a new record.
     */
    CREATE,
    /**
     * Operation to append data to an existing record or table.
     */
    APPEND,
    /**
     * Operation to delete a record.
     */
    DELETE
}