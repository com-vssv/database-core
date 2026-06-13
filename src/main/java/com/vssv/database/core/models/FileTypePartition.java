package com.vssv.database.core.models;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;

public class FileTypePartition extends Partition {
    public final String filePath;
    public final File file;

    public FileTypePartition(int partitionID, String basePath, String fileExtension) throws IOException {
        super(PartitionType.File, partitionID);
        filePath = basePath + "/" + partitionID + "." + fileExtension;
        file = new File(filePath);
        if (!file.exists()) {
            boolean result = file.createNewFile();
            if (!result) {
                throw new IllegalArgumentException("The given path does not exists.");
            }
        }
    }
}