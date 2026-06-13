package com.vssv.database.core.services;

import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.PartitionType;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for managing the lifecycle and retrieval of partitions.
 *
 * @param <P> The specific type of {@link Partition} managed by this class.
 */
@RequiredArgsConstructor
public abstract class PartitionManager<P extends Partition> {
    
    /**
     * The current number of partitions initialized by the manager.
     */
    private int noOfPartitions = 0;
    
    /**
     * An internal mapping of partition identifiers to their corresponding partition objects.
     */
    private final Map<Integer, P> partitionMap = new TreeMap<>();
    
    /**
     * The base directory path where partitions are stored.
     */
    public final String basePath;
    
    /**
     * The type of partition this manager handles.
     */
    public final PartitionType partitionType;

    /**
     * Increases the number of active partitions to the specified amount.
     * New partitions are initialized sequentially up to the requested number.
     * It is not possible to decrease the number of partitions.
     *
     * @param newNoOfPartitions The desired total number of partitions.
     * @throws IllegalArgumentException If the new number is less than the current number.
     * @throws IOException If an I/O error occurs during partition initialization.
     */
    public void setNoOfPartitions(int newNoOfPartitions) throws IOException {
        if (newNoOfPartitions < noOfPartitions){
            throw new IllegalArgumentException("No of Partitions can't be decreased on a live database.");
        }
        while(noOfPartitions < newNoOfPartitions){
            partitionMap.put(noOfPartitions, initializePartition(noOfPartitions));
            noOfPartitions++;
        }
    }

    /**
     * Retrieves a partition by its unique identifier.
     *
     * @param identifier The ID of the partition to retrieve.
     * @return The partition object, or null if the partition does not exist or has not been initialized.
     */
    public P getPartition(int identifier){
        return partitionMap.get(identifier);
    }

    /**
     * Initializes a new partition instance. Must be implemented by subclasses to provide
     * the correct physical storage creation logic for the specific partition type.
     *
     * @param identifier The ID for the new partition.
     * @return The newly initialized partition.
     * @throws IOException If an error occurs during initialization (e.g., file creation).
     */
    protected abstract P initializePartition(int identifier) throws IOException;
}