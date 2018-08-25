package com.corkili.pa.common.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.corkili.pa.common.exception.NotInitializedException;
import com.corkili.pa.common.util.IUtils;

public class C3P0 {

    private static final Logger LOG = LoggerFactory.getLogger(C3P0.class);

    private static C3P0 instance;

    private boolean initialized;

    private ComboPooledDataSource dataSource;

    public static C3P0 getInstance() {
        if (instance == null) {
            synchronized (C3P0.class) {
                if (instance == null) {
                    instance = new C3P0();
                }
            }
        }
        return instance;
    }

    private C3P0() {

    }

    public void init(String dbConfig) {
        if (initialized) {
            LOG.warn("duplicate init C3P0");
            return;
        } else {
            initialized = true;
        }
        try {
            dataSource = new ComboPooledDataSource(dbConfig);
        } catch (Exception e) {
            LOG.error("init C3P0 failed, dbConfig - {}, exception - {}", dbConfig, IUtils.stringifyError(e));
        }
    }

    public Connection getConnection() throws SQLException {
        checkInit();
        return dataSource.getConnection();
    }

    public void close(AutoCloseable... closeables) {
        Arrays.stream(closeables).forEach(closeable -> {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                LOG.error(IUtils.format("close failed - {}", IUtils.stringifyError(e)));
            }
        });
    }

    private void checkInit() {
        if (!initialized) {
            throw new NotInitializedException("C3P0 is not init");
        }
    }

}
