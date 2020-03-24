/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.dto.CategoryRecipeDTO;
import com.cooking.dev.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynh
 */
public class CategoryRecipeDAO implements Serializable {

    private static final String SQL_SAVE_NOT_EXISTS
            = "IF NOT EXISTS (SELECT [Id] FROM [dbo].[tblCategoryRecipe] WHERE [RecipeId] = ? AND [CategoryId] = ?)\n"
            + "BEGIN\n"
            + "INSERT INTO [dbo].[tblCategoryRecipe]([RecipeId],[CategoryId])\n"
            + "VALUES(?,?)\n"
            + "END";

    /**
     * Save a categoryRecipeDTO
     *
     * @param dto
     * @return
     */
    public boolean save(CategoryRecipeDTO dto) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                ps.setInt(1, dto.getRecipeId());
                ps.setInt(2, dto.getCategoryId());
                ps.setInt(3, dto.getRecipeId());
                ps.setInt(4, dto.getCategoryId());
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
