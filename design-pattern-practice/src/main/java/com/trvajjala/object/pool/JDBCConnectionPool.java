package com.trvajjala.object.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {

    private String driver;
    private String connectionUrl;
    private String username;
    private String password;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JDBCConnectionPool() {

    }

    public static class Builder {

        JDBCConnectionPool connectionPool;

        public Builder() {
            connectionPool = new JDBCConnectionPool();
        }

        public Builder usingDriver(String driver) {
            try {
                Class.forName(driver);
            } catch (final ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return this;
        }

        public Builder withUrl(String url) {
            connectionPool.setConnectionUrl(url);
            return this;
        }

        public Builder withUsername(String username) {
            connectionPool.setUsername(username);
            return this;
        }

        public Builder withPassword(String password) {
            connectionPool.setPassword(password);
            return this;
        }

        public JDBCConnectionPool build(int minCount, int maxCount, long validationInterval) {

            connectionPool.prepare(minCount, maxCount, validationInterval);

            return connectionPool;

        }

    }

    @Override
    protected Connection createObject() {
        Connection conn = null;
        try {// "jdbc:mysql://localhost:3306/test"
            conn = DriverManager.getConnection(connectionUrl, username, password);
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    @Override
    protected void prepare(int minCount, int maxCount, long validationInterval) {
        // TODO Auto-generated method stub
        super.prepare(minCount, maxCount, validationInterval);
    }

}
