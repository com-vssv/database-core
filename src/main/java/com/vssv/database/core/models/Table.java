package com.vssv.database.core.models;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public abstract class Table implements Serializable, Comparable<String> {
    public final Integer partitionIdentifier;
    public final String tableName;

    @Override
    public int compareTo(String o) {
        return tableName.compareTo(o);
    }
}
