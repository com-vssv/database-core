package com.vssv.database.core.models;

import lombok.RequiredArgsConstructor;

/**
 * Represents an abstract data partition, defining a specific segment of storage 
 * within a database cluster. Partitions allow data to be distributed based on 
 * an identifier and a partition type.
 */
@RequiredArgsConstructor
public abstract class Partition {
    
    /**
     * The type of this partition (e.g., File, Folder).
     */
    public final PartitionType partitionType;
    
    /**
     * The unique identifier for this partition.
     */
    public final int partitionID;
}