package com.flycode.CRIBSearch.interfaces;

import com.flycode.CRIBSearch.PadSql.PadSqlUtil;

public interface DBdependent {
    void DBaccess(PadSqlUtil padsql);
}
