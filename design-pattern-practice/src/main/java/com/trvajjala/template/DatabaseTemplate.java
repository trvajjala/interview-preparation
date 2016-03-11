package com.trvajjala.template;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public abstract class DatabaseTemplate {

    DataSource dataSource;

    public void execute(String sqlString) {

    }

    public <T> void save(T t) {

    }

    public void deleteById(String tableName, int id) throws SQLException {

        final Connection conn = getConnection();
        final Statement stm = conn.createStatement();

        stm.close();
        conn.close();
    }

    private Connection getConnection() throws SQLException {

        final Connection conn = dataSource.getConnection();

        return conn;
    }

}
