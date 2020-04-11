/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.jaxb.Ingredient;
import com.cooking.dev.util.DBUtils;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private static final String SQL_SELECT_TOP
            = "SELECT TOP (?) [Id]\n"
            + "      ,[Name]\n"
            + "      ,[Image]\n"
            + "      ,[Link]\n"
            + "      ,[Description]\n"
            + "      ,[Price]\n"
            + "  FROM [dbo].[tblIngredient]"
            + "ORDER BY [Id] DESC";

    private static final String SQL_FIND_LIKE_NAME
            = "SELECT TOP (10) [Id]\n"
            + "      ,[Name]\n"
            + "      ,[Image]\n"
            + "      ,[Link]\n"
            + "      ,[Description]\n"
            + "      ,[Price]\n"
            + "  FROM [dbo].[tblIngredient]\n"
            + "  WHERE [Name] LIKE ?\n"
            + "  ORDER BY [Id] DESC";

    private List<Ingredient> listOfIngredients;

    public List<Ingredient> getListOfIngredients() {
        return listOfIngredients;
    }

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

    /**
     *
     * @param topNum
     * @throws SQLException
     */
    public void findTop(int topNum) throws SQLException {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_SELECT_TOP)) {
                ps.setInt(1, topNum);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(BigInteger.valueOf(rs.getInt("Id")));
                        ingredient.setName(rs.getString("Name"));
                        ingredient.setLink(rs.getString("Link"));
                        ingredient.setImage(rs.getString("Image"));
                        ingredient.setDescription(rs.getString("Description"));
                        ingredient.setPrice(rs.getInt("Price"));
                        if (listOfIngredients == null) {
                            listOfIngredients = new ArrayList<>();
                        }
                        listOfIngredients.add(ingredient);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param likeName
     * @throws SQLException
     */
    public void findByLikeName(String likeName) throws SQLException {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_FIND_LIKE_NAME)) {
                ps.setNString(1, likeName + '%');
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(BigInteger.valueOf(rs.getInt("Id")));
                        ingredient.setName(rs.getString("Name"));
                        ingredient.setLink(rs.getString("Link"));
                        ingredient.setImage(rs.getString("Image"));
                        ingredient.setDescription(rs.getString("Description"));
                        ingredient.setPrice(rs.getInt("Price"));
                        if (listOfIngredients == null) {
                            listOfIngredients = new ArrayList<>();
                        }
                        listOfIngredients.add(ingredient);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
