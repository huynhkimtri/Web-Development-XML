/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.jaxb.Path;
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
public class CategoryDAO implements Serializable {

    private static final String SQL_SAVE
            = "INSERT INTO [dbo].[tblCategory]([Id],[Name],[Link])\n"
            + "VALUES(?, ?, ?)";
    private static final String SQL_SAVE_NOT_EXISTS
            = "IF NOT EXISTS (SELECT [Name] FROM [dbo].[tblCategory] WHERE [Id] = ?)\n"
            + "BEGIN\n"
            + "INSERT INTO [dbo].[tblCategory]([Id],[Name],[Link])\n"
            + "VALUES(?, ?, ?)\n"
            + "END";
    private static final String SQL_SAVE_CATE
            = "INSERT INTO [dbo].[tblCategory]([RecipeId],[CategoryId])\n"
            + "VALUES(?, ?)";

    /**
     * Sets the designated parameter to the given Java value.
     *
     * @param ps The <code>PreparedStatement</code> object
     * @param p The <code>Recipe</code> object
     * @throws SQLException
     */
    private static void setDesignatedParams(PreparedStatement ps, Path p)
            throws SQLException {
        ps.setInt(1, p.getId().intValue());
        ps.setInt(2, p.getId().intValue());
        ps.setString(3, p.getValue());
        ps.setString(4, p.getLink());
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean save(Path p) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                setDesignatedParams(ps, p);
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param list
     * @return
     */
    public boolean save(List<Path> list) {
        try (Connection con = DBUtils.getConnection()) {
            int results;
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                con.setAutoCommit(false);
                for (Path item : list) {
                    setDesignatedParams(ps, item);
                    ps.addBatch();
                }
                results = ps.executeBatch().length;
                con.commit();
            }
            return results > 0;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param recipeId
     * @param categoryId
     * @return
     */
    public boolean saveCategory(int recipeId, int categoryId) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_CATE)) {
                ps.setInt(1, recipeId);
                ps.setInt(2, categoryId);
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
