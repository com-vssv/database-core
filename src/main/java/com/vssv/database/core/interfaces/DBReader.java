package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.Query;
import com.vssv.database.core.models.ReadRecord;

public interface DBReader <R extends ReadRecord, Q extends Query>{
    public R readFromStorage(Q query);
}
