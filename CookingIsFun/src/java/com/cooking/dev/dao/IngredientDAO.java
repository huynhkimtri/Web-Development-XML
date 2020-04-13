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
import java.sql.CallableStatement;
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

    private List<Ingredient> listOfIngredients;

    public List<Ingredient> getListOfIngredients() {
        return listOfIngredients;
    }

    /**
     * Save an ingredient
     *
     * @param ingredient
     * @return
     */
    public boolean save(Ingredient ingredient) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs = con.prepareCall("{call stpSaveIngredient(?,?,?,?,?,?)}")) {
                cs.setInt(1, ingredient.getId().intValue());
                cs.setString(2, ingredient.getName());
                cs.setString(3, ingredient.getImage());
                cs.setString(4, ingredient.getLink());
                cs.setString(5, ingredient.getDescription());
                cs.setInt(6, ingredient.getPrice());
                return cs.executeUpdate() > 0;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Get top ingredients, descending order by id
     *
     * @param topNum
     * @throws SQLException
     */
    public void findTop(int topNum) throws SQLException {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs = con.prepareCall("{call stpGetTopIngredient(?)")) {
                cs.setInt(1, topNum);
                try (ResultSet rs = cs.executeQuery()) {
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
     * Get ingredients by name contains likeName using SQL Stored Procedure
     *
     * @param likeName
     */
    public void findByLikeName(String likeName) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs
                    = con.prepareCall("{call stpGetIngredientByLikeName(?)}")) {
                cs.setNString(1, likeName);
                try (ResultSet rs = cs.executeQuery()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get ingredients by name contains likeName with pagination
     *
     * @param likeName
     * @param pageSize
     * @param pageNum
     */
    public void findByLikeName(String likeName, int pageSize, int pageNum) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs
                    = con.prepareCall("{call stpGetIngredientByLikeNameWithPagination(?,?,?)}")) {
                cs.setNString(1, likeName);
                cs.setInt(2, pageSize);
                cs.setInt(3, pageNum);
                try (ResultSet rs = cs.executeQuery()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
