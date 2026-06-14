package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.Query;
import com.vssv.database.core.models.ReadRecord;

/**
 * Interface defining the read operations for the database.
 * Implementations of this interface are responsible for executing queries
 * and retrieving data records from the underlying storage.
 *
 * @param <R> The type of {@link ReadRecord} returned by the reader.
 * @param <Q> The type of {@link Query} accepted by the reader.
 */
public interface ReadOperations<R extends ReadRecord, Q extends Query> {
    
    /**
     * Executes the given query and retrieves the corresponding record from storage.
     *
     * @param query The query to execute.
     * @return The retrieved record, or null if no matching record is found.
     */
    public R readFromStorage(Q query);
}