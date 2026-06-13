package com.vssv.database.core.interfaces;

import com.vssv.database.core.models.WriteRecord;

public interface DBWriter<W extends WriteRecord> {
    public boolean persistRecord(W writeRecord);
}
