package com.javawebinar.basejava.webapp.sql;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException exceptionConvert(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
