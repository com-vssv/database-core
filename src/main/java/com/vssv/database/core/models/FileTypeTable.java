package com.vssv.database.core.models;

public class FileTypeTable extends Table{
    public int fileLocation = -1;
    public FileTypeTable(Integer partitionIdentifier, String tableName) {
        super(partitionIdentifier, tableName);
    }
}
