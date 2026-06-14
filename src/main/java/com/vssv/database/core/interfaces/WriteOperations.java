package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.Partition;
import com.vssv.database.core.models.Table;
import com.vssv.database.core.models.WriteRecord;

import java.io.IOException;

/**
 * Interface defining the write operations for the database.
 * Implementations of this interface are responsible for creating tables and persisting data records
 * to the underlying storage.
 *
 * @param <W> The type of {@link WriteRecord} accepted by the writer.
 * @param <P> The type of {@link Partition} accepted by the writer.
 * @param <T> The type of {@link Table} accepted by the writer.
 */
public interface WriteOperations<W extends WriteRecord, P extends Partition, T extends Table> {
    
    /**
     * Creates a new table with the specified name.
     *
     * @param tableName The name for the new table.
     * @return True if the table was created successfully, false otherwise.
     * @throws IOException If an I/O error occurs during table creation.
     */
    public boolean createTable(String tableName) throws IOException;
    
    /**
     * Persists the given record to the database.
     *
     * @param writeRecord The record to be written.
     * @return True if the record was successfully persisted, false otherwise.
     * @throws IOException If an I/O error occurs during persistence.
     */
    public boolean persistRecord(W writeRecord) throws IOException;
}