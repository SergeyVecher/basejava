package com.javawebinar.basejava.webapp.sql;

import com.javawebinar.basejava.webapp.exception.StorageException;

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
            throw ExceptionUtil.exceptionConvert(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> transaction) {
        try (Connection connection = factory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                T res = transaction.execute(connection);
                connection.commit();
                return res;
            } catch (SQLException e) {
                connection.rollback();
                throw ExceptionUtil.exceptionConvert(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}

