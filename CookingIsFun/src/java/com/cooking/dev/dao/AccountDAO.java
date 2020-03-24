/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynh
 */
public class AccountDAO implements Serializable {

    private static final String SQL_LOGIN
            = "SELECT isAdmin FROM [dbo].[tblAccount]\n"
            + "WHERE username = ? AND password = ?";

    /**
     * Check login
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_LOGIN)) {
                ps.setString(1, username);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getBoolean("isAdmin")) {
                        return true;
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
