package com.vssv.database.core.services;

import com.vssv.database.core.models.FolderTypePartition;
import com.vssv.database.core.models.PartitionType;

import java.io.IOException;

public class FolderTypePartitionManager extends PartitionManager<FolderTypePartition> {
    public FolderTypePartitionManager(String basePath) {
        super(basePath, PartitionType.Folder);
    }

    @Override
    protected FolderTypePartition initializePartition(int identifier) throws IOException {
        return new FolderTypePartition(identifier, basePath);
    }
}
