package com.util;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DbUtilTest {

    @Test
    public void getDbConnection() {
        try {
            assertFalse("Соединение с бд не установлено",DbUtil.getDbConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}