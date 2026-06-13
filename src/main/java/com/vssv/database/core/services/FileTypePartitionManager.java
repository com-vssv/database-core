package com.vssv.database.core.services;

import com.vssv.database.core.models.FileTypePartition;
import com.vssv.database.core.models.PartitionType;

import java.io.IOException;

public class FileTypePartitionManager extends PartitionManager<FileTypePartition>{
    public final String fileExtension;
    public FileTypePartitionManager(String basePath, String fileExtension) {
        super(basePath, PartitionType.File);
        this.fileExtension = fileExtension;
    }

    @Override
    protected FileTypePartition initializePartition(int identifier) throws IOException{
        return new FileTypePartition(identifier, basePath, fileExtension);
    }
}
