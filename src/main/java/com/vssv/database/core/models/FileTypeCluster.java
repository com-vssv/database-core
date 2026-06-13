package com.vssv.database.core.models;

import java.util.List;

public class FileTypeCluster extends Cluster<FileTypeTable> {
    public FileTypeCluster(String basePath, List<FileTypeTable> tables) {
        super(basePath, tables);
    }
}
