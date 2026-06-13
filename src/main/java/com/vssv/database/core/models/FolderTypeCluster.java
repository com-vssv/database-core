package com.vssv.database.core.models;

import java.util.List;

public class FolderTypeCluster extends Cluster<FolderTypeTable> {
    public FolderTypeCluster(String basePath, List<FolderTypeTable> tables) {
        super(basePath, tables);
    }
}
