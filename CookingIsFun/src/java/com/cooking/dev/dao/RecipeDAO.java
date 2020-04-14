/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.jaxb.IngredientItem;
import com.cooking.dev.jaxb.InstructionItem;
import com.cooking.dev.jaxb.ListIngredients;
import com.cooking.dev.jaxb.ListIntructions;
import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.util.DBUtils;
import com.cooking.dev.util.UnicodeUtils;
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
public class RecipeDAO implements Serializable {

    private List<Recipe> listOfRecipes;

    public List<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

    /**
     * Sets the designated parameter to the given Java value.
     *
     * @param statement The <code>PreparedStatement</code> object
     * @param recipe The <code>Recipe</code> object
     * @throws SQLException
     */
    private static void setDesignatedParams(CallableStatement statement, Recipe recipe)
            throws SQLException {
        statement.setInt(1, recipe.getId().intValue());
        statement.setString(2, UnicodeUtils.encode(recipe.getName().trim()));
        statement.setString(3, recipe.getImage().trim());
        statement.setString(4, recipe.getLink().trim());
        statement.setString(5, UnicodeUtils.encode(recipe.getDescription().trim()));
        statement.setString(6, recipe.getServings());
        statement.setInt(7, recipe.getPrepTime().intValue());
        statement.setInt(8, recipe.getCookTime().intValue());
    }

    /**
     * save a recipe using SQL Stored Procedure
     *
     * @param recipe
     * @return
     */
    public boolean save(Recipe recipe) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs
                    = con.prepareCall("{call stpSaveRecipe(?,?,?,?,?,?,?,?)}")) {
                con.setAutoCommit(false);

                cs.setInt(1, recipe.getId().intValue());
                cs.setString(2, UnicodeUtils.encode(recipe.getName().trim()));
                cs.setString(3, recipe.getImage().trim());
                cs.setString(4, recipe.getLink().trim());
                cs.setString(5, UnicodeUtils.encode(recipe.getDescription().trim()));
                cs.setString(6, recipe.getServings());
                cs.setInt(7, recipe.getPrepTime().intValue());
                cs.setInt(8, recipe.getCookTime().intValue());

                int row = cs.executeUpdate();
                boolean inserted = false;
                if (row > 0) {
                    int recId = recipe.getId().intValue();

                    List<InstructionItem> listOfInstruction
                            = recipe.getListIntructions().getInstruction();
                    for (InstructionItem item : listOfInstruction) {
                        try (CallableStatement stm
                                = con.prepareCall("{call stpSaveInstructionItem(?,?,?)}")) {
                            stm.setInt(1, recId);
                            stm.setInt(2, item.getStep().intValue());
                            stm.setString(3, UnicodeUtils.encode(item.getDetail()).trim());
                            inserted = stm.executeUpdate() > 0;
                        }
                    }

                    List<IngredientItem> listOfIngredient
                            = recipe.getListIngredients().getIngredient();
                    for (IngredientItem item : listOfIngredient) {
                        try (CallableStatement stm
                                = con.prepareCall("{call stpSaveIngredientItem(?,?,?,?)}")) {
                            stm.setInt(1, recId);
                            stm.setString(2, UnicodeUtils.encode(item.getName().trim()));
                            stm.setString(3, UnicodeUtils.encode(item.getUnit().trim()));
                            stm.setString(4, item.getQuantity());
                            inserted = stm.executeUpdate() > 0;
                        }
                    }
                }
                con.commit();
                return inserted;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Save recipe given a list of recipes using SQL Stored Procudure
     *
     * @param list
     * @return
     */
    public boolean save(List<Recipe> list) {
        try (Connection con = DBUtils.getConnection()) {
            int results;
            try (CallableStatement cs
                    = con.prepareCall("{call stpSaveRecipe(?,?,?,?,?,?,?,?)}")) {
                con.setAutoCommit(false);
                for (Recipe r : list) {
                    setDesignatedParams(cs, r);
                }
                results = cs.executeBatch().length;
                con.commit();
            }
            return results > 0;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Get top recipes using SQL Stored Procedure
     *
     * @param topNum
     */
    public void findTopUsingProcedure(int topNum) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs = con.prepareCall("{call stpGetTopRecipe (?)}")) {
                cs.setInt(1, topNum);
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        Recipe recipe = new Recipe();
                        recipe.setId(BigInteger.valueOf(rs.getInt("Id")));
                        recipe.setName(rs.getString("Name"));
                        recipe.setLink(rs.getString("Link"));
                        recipe.setImage(rs.getString("Image"));
                        recipe.setDescription(rs.getString("Description"));
                        recipe.setServings(rs.getString("Servings"));
                        recipe.setPrepTime(BigInteger.valueOf(rs.getInt("PrepTime")));
                        recipe.setCookTime(BigInteger.valueOf(rs.getInt("CookTime")));
                        if (listOfRecipes == null) {
                            listOfRecipes = new ArrayList<>();
                        }
                        listOfRecipes.add(recipe);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get recipe by id using SQL Stored Procedure
     *
     * @param id
     * @return Recipe
     */
    public Recipe findByIdUsingProcedure(int id) {
        Recipe recipe = null;
        InstructionItem instruction;
        IngredientItem ingredient;
        ListIntructions instructions;
        ListIngredients ingredients;
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs = con.prepareCall("{call stpGetRecipeById(?)}")) {
                cs.setInt(1, id);
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        recipe = new Recipe();
                        recipe.setId(BigInteger.valueOf(id));
                        recipe.setName(rs.getString("Name"));
                        recipe.setLink(rs.getString("Link"));
                        recipe.setImage(rs.getString("Image"));
                        recipe.setDescription(rs.getString("Description"));
                        recipe.setServings(rs.getString("Servings"));
                        recipe.setPrepTime(BigInteger.valueOf(rs.getInt("PrepTime")));
                        recipe.setCookTime(BigInteger.valueOf(rs.getInt("CookTime")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (recipe != null) {
                try (CallableStatement cs
                        = con.prepareCall("{call stpGetInstructionsByRecipeId(?)}")) {
                    cs.setInt(1, id);
                    instructions = new ListIntructions();
                    try (ResultSet rs = cs.executeQuery()) {
                        while (rs.next()) {
                            instruction = new InstructionItem();
                            instruction.setStep(BigInteger.valueOf(rs.getInt("Step")));
                            instruction.setDetail(rs.getString("Detail"));
                            instructions.getInstruction().add(instruction);
                        }
                        recipe.setListIntructions(instructions);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                try (CallableStatement cs
                        = con.prepareCall("{call stpGetIngredientsByRecipeId(?)}")) {
                    cs.setInt(1, id);
                    ingredients = new ListIngredients();
                    try (ResultSet rs = cs.executeQuery()) {
                        while (rs.next()) {
                            ingredient = new IngredientItem();
                            ingredient.setName(rs.getString("Name"));
                            ingredient.setQuantity(rs.getString("Quantity"));
                            ingredient.setUnit(rs.getString("Unit"));
                            ingredients.getIngredient().add(ingredient);
                        }
                        recipe.setListIngredients(ingredients);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                return recipe;
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get recipes by name contains likeName using SQL Stored Procedure
     *
     * @param likeName
     */
    public void findByNameUsingProcedure(String likeName) {
        try (Connection con = DBUtils.getConnection()) {
            try (CallableStatement cs
                    = con.prepareCall("{call stpGetRecipeByLikeName(?)}")) {
                cs.setNString(1, likeName);
                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        Recipe recipe = new Recipe();
                        recipe.setId(BigInteger.valueOf(rs.getInt("Id")));
                        recipe.setName(rs.getString("Name"));
                        recipe.setLink(rs.getString("Link"));
                        recipe.setImage(rs.getString("Image"));
                        recipe.setDescription(rs.getString("Description"));
                        recipe.setServings(rs.getString("Servings"));
                        recipe.setPrepTime(BigInteger.valueOf(rs.getInt("PrepTime")));
                        recipe.setCookTime(BigInteger.valueOf(rs.getInt("CookTime")));
                        if (listOfRecipes == null) {
                            listOfRecipes = new ArrayList<>();
                        }
                        listOfRecipes.add(recipe);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param arr
     */
    public void findByCategories(int[] arr) {
        int leng = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leng - 1; i++) {
            sb.append("?,");
        }
        sb.append('?');
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement statement
                    = con.prepareStatement("SELECT [Id]\n"
                            + "      ,[Name]\n"
                            + "      ,[Image]\n"
                            + "      ,[Link]\n"
                            + "      ,[Description]\n"
                            + "      ,[Servings]\n"
                            + "      ,[PrepTime]\n"
                            + "      ,[CookTime]\n"
                            + "	  ,(CookTime + PrepTime) AS Time\n"
                            + "  FROM [dbo].[tblRecipe] Recipe,\n"
                            + "  (SELECT [RecipeId] FROM [dbo].[tblCategoryRecipe] \n"
                            + "  WHERE [CategoryId] IN (" + sb.toString() + ") GROUP BY [RecipeId]) CateRecipe\n"
                            + "  WHERE CateRecipe.RecipeId = Recipe.Id	\n"
                            + "  ORDER BY Time")) {
                for (int i = 0; i < leng; i++) {
                    statement.setInt(i + 1, arr[i]);
                }
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        Recipe recipe = new Recipe();
                        recipe.setId(BigInteger.valueOf(rs.getInt("Id")));
                        recipe.setName(rs.getString("Name"));
                        recipe.setLink(rs.getString("Link"));
                        recipe.setImage(rs.getString("Image"));
                        recipe.setDescription(rs.getString("Description"));
                        recipe.setServings(rs.getString("Servings"));
                        recipe.setPrepTime(BigInteger.valueOf(rs.getInt("PrepTime")));
                        recipe.setCookTime(BigInteger.valueOf(rs.getInt("CookTime")));
                        if (listOfRecipes == null) {
                            listOfRecipes = new ArrayList<>();
                        }
                        listOfRecipes.add(recipe);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
