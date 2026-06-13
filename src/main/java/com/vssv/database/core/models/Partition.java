package com.vssv.database.core.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Partition {
    public final PartitionType partitionType;
    public final int partitionID;
}
