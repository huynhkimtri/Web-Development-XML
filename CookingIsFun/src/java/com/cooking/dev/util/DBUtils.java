/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author huynh
 */
public class DBUtils {

    /**
     * Establish a connection with the data source
     *
     * @return a connection to the data source
     * @throws NamingException
     * @throws SQLException
     */
    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        DataSource dataSource = (DataSource) tomcatContext.lookup("CookingIsFun_DataSource");
        Connection con = dataSource.getConnection();
        return con;
    }

}
