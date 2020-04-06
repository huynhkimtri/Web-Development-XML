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

    private static final String SQL_SAVE
            = "INSERT INTO [dbo].[tblRecipe]([Id],[Name],[Link])\n"
            + "VALUES(?, ?, ?)";
    private static final String SQL_SAVE_NOT_EXISTS
            = "IF NOT EXISTS (SELECT [Name] FROM [dbo].[tblRecipe] WHERE [Id] = ?)\n"
            + "BEGIN\n"
            + "INSERT INTO [dbo].[tblRecipe]([Id],[Name],[Image],[Link],[Description],[Servings],[PrepTime],[CookTime])\n"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n"
            + "END";
    private static final String SQL_SAVE_INSTRUCTION
            = "INSERT INTO [dbo].[tblListOfInstruction]([RecipeId],[Step],[Detail])\n"
            + "VALUES(?, ?, ?)";
    private static final String SQL_SAVE_INGREDIENT
            = "INSERT INTO [dbo].[tblListOfIngredient]([RecipeId],[Name],[Unit],[Quantity])\n"
            + "VALUES(?, ?, ?, ?)";
    private static final String SQL_SELECT_TOP
            = "SELECT TOP (?) [Id]\n"
            + "      ,[Name]\n"
            + "      ,[Image]\n"
            + "      ,[Link]\n"
            + "      ,[Description]\n"
            + "      ,[Servings]\n"
            + "      ,[PrepTime]\n"
            + "      ,[CookTime]\n"
            + "  FROM [dbo].[tblRecipe]"
            + "ORDER BY [Id] DESC";
    private static final String SQL_FIND_BY_ID
            = "SELECT [Name]\n"
            + "      ,[Image]\n"
            + "      ,[Link]\n"
            + "      ,[Description]\n"
            + "      ,[Servings]\n"
            + "      ,[PrepTime]\n"
            + "      ,[CookTime]\n"
            + "FROM [dbo].[tblRecipe]"
            + "WHERE [Id] = ?";
    private static final String SQL_FIND_INSTRUCTIONS
            = "SELECT [Id]\n"
            + "      ,[Step]\n"
            + "      ,[Detail]\n"
            + "FROM [dbo].[tblListOfInstruction]\n"
            + "WHERE [RecipeId] = ?";
    private static final String SQL_FIND_INGREDIENTS
            = "SELECT [Id]\n"
            + "      ,[Name]\n"
            + "      ,[Unit]\n"
            + "      ,[Quantity]\n"
            + "FROM [dbo].[tblListOfIngredient]\n"
            + "WHERE [RecipeId] = ?";
    private static final String SQL_FIND_LIKE_NAME
            = "SELECT TOP (1000) [Id]\n"
            + "      ,[Name]\n"
            + "      ,[Image]\n"
            + "      ,[Link]\n"
            + "      ,[Description]\n"
            + "      ,[Servings]\n"
            + "      ,[PrepTime]\n"
            + "      ,[CookTime]\n"
            + "  FROM [CookingIsFun].[dbo].[tblRecipe]\n"
            + "  WHERE [Name] LIKE ?\n"
            + "  ORDER BY [Id] DESC";

    private List<Recipe> listOfRecipes;

    public List<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

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
        ps.setString(3, UnicodeUtils.encode(obj.getName().trim()));
        ps.setString(4, obj.getImage().trim());
        ps.setString(5, obj.getLink().trim());
        ps.setString(6, UnicodeUtils.encode(obj.getDescription().trim()));
        ps.setString(7, obj.getServings());
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
                con.setAutoCommit(false);

                ps.setInt(1, r.getId().intValue());
                ps.setInt(2, r.getId().intValue());
                ps.setString(3, UnicodeUtils.encode(r.getName().trim()));
                ps.setString(4, r.getImage().trim());
                ps.setString(5, r.getLink().trim());
                ps.setString(6, UnicodeUtils.encode(r.getDescription().trim()));
                ps.setString(7, r.getServings());
                ps.setInt(8, r.getPrepTime().intValue());
                ps.setInt(9, r.getCookTime().intValue());

                int row = ps.executeUpdate();
                boolean inserted = false;
                if (row > 0) {
                    int recId = r.getId().intValue();

                    List<InstructionItem> listOfInstruction = r.getListIntructions().getInstruction();
                    for (InstructionItem item : listOfInstruction) {
                        try (PreparedStatement pstm = con.prepareStatement(SQL_SAVE_INSTRUCTION)) {
                            pstm.setInt(1, recId);
                            pstm.setInt(2, item.getStep().intValue());
                            pstm.setString(3, UnicodeUtils.encode(item.getDetail()).trim());
                            inserted = pstm.executeUpdate() > 0;
                        }
                    }

                    List<IngredientItem> listOfIngredient = r.getListIngredients().getIngredient();
                    for (IngredientItem item : listOfIngredient) {
                        try (PreparedStatement pstm = con.prepareStatement(SQL_SAVE_INGREDIENT)) {
                            pstm.setInt(1, recId);
                            pstm.setString(2, UnicodeUtils.encode(item.getName().trim()));
                            pstm.setString(3, UnicodeUtils.encode(item.getUnit().trim()));
                            pstm.setString(4, item.getQuantity());
                            inserted = pstm.executeUpdate() > 0;
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Recipe findById(int id) throws SQLException {
        Recipe recipe = null;
        InstructionItem instruction;
        IngredientItem ingredient;
        ListIntructions instructions;
        ListIngredients ingredients;
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
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
                try (PreparedStatement ps = con.prepareStatement(SQL_FIND_INSTRUCTIONS)) {
                    ps.setInt(1, id);
                    instructions = new ListIntructions();
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            instruction = new InstructionItem();
                            instruction.setStep(BigInteger.valueOf(rs.getInt("Step")));
                            instruction.setDetail(rs.getString("Detail"));
                            instructions.getInstruction().add(instruction);
                        }
                        recipe.setListIntructions(instructions);
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(SQL_FIND_INGREDIENTS)) {
                    ps.setInt(1, id);
                    ingredients = new ListIngredients();
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            ingredient = new IngredientItem();
                            ingredient.setName(rs.getString("Name"));
                            ingredient.setQuantity(rs.getString("Quantity"));
                            ingredient.setUnit(rs.getString("Unit"));
                            ingredients.getIngredient().add(ingredient);
                        }
                        recipe.setListIngredients(ingredients);
                    }
                }
                return recipe;
            }
        } catch (NamingException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param likeName
     */
    public void findByName(String likeName) {
        try (Connection con = DBUtils.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(SQL_FIND_LIKE_NAME)) {
                ps.setNString(1, '%' + likeName + '%');
                try (ResultSet rs = ps.executeQuery()) {
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
