package com.vssv.database.core.models;

import lombok.RequiredArgsConstructor;

/**
 * Represents an abstract record to be written into the database, specifying the table,
 * operation type, and the data to be written.
 *
 * @param <TS> The type of {@link TableSchema} representing the data to be written.
 */
@RequiredArgsConstructor
public abstract class WriteRecord<TS extends TableSchema> {
    
    /**
     * The name of the table to which the record will be written.
     */
    public final String tableName;
    
    /**
     * The type of write operation to be performed (e.g., CREATE, APPEND, DELETE).
     */
    public final WriteOperationType writeOperationType;
    
    /**
     * The actual data to be written, conforming to the table's schema.
     */
    public final TS writeValue;
}