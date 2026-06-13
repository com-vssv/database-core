package com.vssv.database.core.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public abstract class Cluster<T extends Table> implements Serializable {
    public final String basePath;
    public final List<T> tables;
    public int noOfPartitions;
}
