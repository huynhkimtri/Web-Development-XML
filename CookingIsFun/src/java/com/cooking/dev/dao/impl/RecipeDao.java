/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao.impl;

import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.util.DBUtils;
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
public class RecipeDao implements Serializable {

    private static final String SQL_SAVE
            = "INSERT INTO [dbo].[tblRecipe]([Id],[Name],[Link])\n"
            + "VALUES(?, ?, ?)";
    private static final String SQL_SAVE_NOT_EXISTS
            = "IF NOT EXISTS (SELECT [Name] FROM [dbo].[tblRecipe] WHERE [Id] = ?)\n"
            + "BEGIN\n"
            + "INSERT INTO [dbo].[tblRecipe]([Id],[Name],[Image],[Link],[Description],[Servings],[PrepTime],[CookTime])\n"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n"
            + "END";

    /**
     * Sets the designated parameter to the given Java value.
     *
     * @param ps The <code>PreparedStatement</code> object
     * @param obj The <code>Recipe</code> object
     * @throws SQLException
     */
    private static void setDesignatedParams(PreparedStatement ps, Recipe obj)
            throws SQLException {
        ps.setInt(1, obj.getId().intValue());
        ps.setInt(2, obj.getId().intValue());
        ps.setString(3, obj.getName());
        ps.setString(4, obj.getImage());
        ps.setString(5, obj.getLink());
        ps.setString(6, obj.getDescription());
        ps.setInt(7, obj.getServings().intValue());
        ps.setInt(8, obj.getPrepTime().intValue());
        ps.setInt(9, obj.getCookTime().intValue());
    }

    /**
     *
     * @param r
     * @return
     */
    public boolean save(Recipe r) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                setDesignatedParams(ps, r);
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param list
     * @return
     */
    public boolean save(List<Recipe> list) {
        try (Connection con = DBUtils.getConnection()) {
            int results;
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                con.setAutoCommit(false);
                for (Recipe r : list) {
                    setDesignatedParams(ps, r);
                }
                results = ps.executeBatch().length;
                con.commit();
            }
            return results > 0;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
