package com.vssv.database.core.models;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Represents an abstract table within the database, storing records and metadata.
 * Tables belong to partitions and manage the underlying clusters of data.
 * It is serializable and comparable by table name.
 */
@RequiredArgsConstructor
public abstract class Table implements Serializable, Comparable<String> {
    
    /**
     * The identifier for the partition this table belongs to.
     */
    public final Integer partitionIdentifier;
    
    /**
     * The unique name of the table.
     */
    public final String tableName;

    @Override
    public int compareTo(String o) {
        return tableName.compareTo(o);
    }
}