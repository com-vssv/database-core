package com.vssv.database.core.models;

import java.io.File;
import java.security.InvalidKeyException;

public class FolderTypePartition extends Partition{
    public final String directoryPath;
    public final File directory;
    public FolderTypePartition(int partitionID, String basePath) {
        super(PartitionType.Folder, partitionID);
        directoryPath = basePath+ "/" + partitionID;
        directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (!result){
                throw new IllegalArgumentException("The given path does not exists.");
            }
        }
    }
}
