package com.corkili.pa.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.corkili.pa.common.util.CheckUtils;

public class DaoUtils {

    public static CachedRowSet query(String sql, Object... params) throws SQLException {
        try (ResultSet resultSet = queryResultSet(sql, params)) {
            if (resultSet != null) {
                RowSetFactory factory = RowSetProvider.newFactory();
                CachedRowSet rowSet = factory.createCachedRowSet();
                rowSet.populate(resultSet);
                return rowSet;
            } else {
                throw new SQLException("resultSet is null");
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public static CachedRowSet query(String sql, int pageSize, int page, Object... params) throws SQLException {
        ResultSet resultSet = queryResultSet(sql, params);
        try {
            if (resultSet != null) {
                RowSetFactory factory = RowSetProvider.newFactory();
                CachedRowSet rowSet = factory.createCachedRowSet();
                rowSet.setPageSize(pageSize);
                rowSet.populate(resultSet, (page - 1) * pageSize + 1);
                return rowSet;
            } else {
                throw new SQLException("resultSet is null");
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            C3P0.getInstance().close(resultSet);
        }
    }

    public static int update(String sql, Object... params) throws SQLException {
        C3P0 c3P0 = C3P0.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = c3P0.getConnection();
            if (CheckUtils.isNotNull(connection)) {
                preparedStatement = connection.prepareStatement(sql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                }
                return preparedStatement.executeUpdate();
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            c3P0.close(connection, preparedStatement);
        }
    }

    private static ResultSet queryResultSet(String sql, Object... params) throws SQLException {
        C3P0 c3P0 = C3P0.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = c3P0.getConnection();
            if (CheckUtils.isNotNull(connection)) {
                preparedStatement = connection.prepareStatement(sql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                }
                return preparedStatement.executeQuery();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            c3P0.close(connection, preparedStatement);
        }
    }

}
