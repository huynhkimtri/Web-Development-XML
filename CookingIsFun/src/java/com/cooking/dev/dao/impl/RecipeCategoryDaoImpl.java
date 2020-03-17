/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao.impl;

import com.cooking.dev.dao.Dao;
import com.cooking.dev.jaxb.Path;
import com.cooking.dev.utility.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynh
 */
public class RecipeCategoryDaoImpl implements Dao<Path>, Serializable {

    @Override
    public List<Path> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Path getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Path t) {
        try (Connection con = DBUtils.getConnection()) {
            String sql = "INSERT INTO [dbo].[tblRecipeCategory]([Id],[Name],[Link])\n"
                    + "VALUES(?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, t.getId().intValue());
                ps.setString(2, t.getValue());
                ps.setString(3, t.getLink());
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean saveList(List<Path> ts) {
        try (Connection con = DBUtils.getConnection()) {
            String sql = "INSERT INTO [dbo].[tblRecipeCategory]([Id],[Name],[Link])\n"
                    + "VALUES(?, ?, ?)";
            int results;
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                con.setAutoCommit(false);
                for (Path item : ts) {
                    ps.setInt(1, item.getId().intValue());
                    ps.setString(2, item.getValue());
                    ps.setString(3, item.getLink());
                    ps.addBatch();
                }
                results = ps.executeBatch().length;
                con.commit();
            }
            return results > 0;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void update(Path t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Path t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
