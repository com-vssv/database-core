package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.WriteRecord;

/**
 * Interface defining the write operations for the database.
 * Implementations of this interface are responsible for persisting data records
 * to the underlying storage.
 *
 * @param <W> The type of {@link WriteRecord} accepted by the writer.
 */
public interface DBWriter<W extends WriteRecord> {
    
    /**
     * Persists the given record to the database.
     *
     * @param writeRecord The record to be written.
     * @return True if the record was successfully persisted, false otherwise.
     */
    public boolean persistRecord(W writeRecord);
}