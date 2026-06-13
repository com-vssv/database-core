package com.vssv.database.core.services;

import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.PartitionType;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
public abstract class PartitionManager<P extends Partition> {
    private int noOfPartitions = 0;
    private final Map<Integer, P> partitionMap = new TreeMap<>();
    public final String basePath;
    public final PartitionType partitionType;

    public void setNoOfPartitions(int newNoOfPartitions) throws IOException {
        if (newNoOfPartitions < noOfPartitions){
            throw new IllegalArgumentException("No of Partitions can't be decreased on a live database.");
        }
        while(noOfPartitions < newNoOfPartitions){
            partitionMap.put(noOfPartitions, initializePartition(noOfPartitions));
            noOfPartitions++;
        }
    }

    public P getPartition(int identifier){
        return partitionMap.get(identifier);
    }

    protected abstract P initializePartition(int identifier) throws IOException;
}
