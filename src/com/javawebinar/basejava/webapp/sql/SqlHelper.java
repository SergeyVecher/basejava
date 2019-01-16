package com.javawebinar.basejava.webapp.sql;

import com.javawebinar.basejava.webapp.exception.ExistStorageException;
import com.javawebinar.basejava.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private ConnectionFactory factory;

    public SqlHelper(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }


    public <T> T execute(String sql, Executor<T> executor) {
        try (Connection connection = factory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            if (e instanceof PSQLException) {
                if (e.getSQLState().equals("23505")) {
                    throw new ExistStorageException(null);
                }
            }
            throw new StorageException(e);
        }
    }
}

