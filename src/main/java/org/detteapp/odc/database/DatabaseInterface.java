package org.detteapp.odc.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseInterface {
    public Connection getConnection();
    public void closeConnection();
    public ResultSet executeQuery(String sql);
    public int executeUpdate(String sql) throws SQLException;
}
