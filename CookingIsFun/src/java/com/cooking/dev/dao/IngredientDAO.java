/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.jaxb.Ingredient;
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
public class IngredientDAO implements Serializable {

    private static final String SQL_SAVE_NOT_EXISTS
            = "IF NOT EXISTS (SELECT [Name] FROM [dbo].[tblIngredient] WHERE [Id] = ?)\n"
            + "BEGIN\n"
            + "INSERT INTO [dbo].[tblIngredient]([Id],[Name],[Image],[Link],[Description],[Price])\n"
            + "VALUES(?, ?, ?, ?, ?, ?)\n"
            + "END";

    /**
     *
     * @param ingredient
     * @return
     */
    public boolean save(Ingredient ingredient) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SAVE_NOT_EXISTS)) {
                ps.setInt(1, ingredient.getId().intValue());
                ps.setInt(2, ingredient.getId().intValue());
                ps.setString(3, ingredient.getName());
                ps.setString(4, ingredient.getImage());
                ps.setString(5, ingredient.getLink());
                ps.setString(6, ingredient.getDescription());
                ps.setInt(7, ingredient.getPrice());
                return ps.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
