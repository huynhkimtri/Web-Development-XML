/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dto;

import java.io.Serializable;

/**
 *
 * @author huynh
 */
public class CategoryRecipeDTO implements Serializable {

    private int id;
    private int recipeId;
    private int categoryId;

    public CategoryRecipeDTO() {
    }

    public CategoryRecipeDTO(int recipeId, int categoryId) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    public CategoryRecipeDTO(int id, int recipeId, int categoryId) {
        this.id = id;
        this.recipeId = recipeId;
        this.categoryId = categoryId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the recipeId
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * @param recipeId the recipeId to set
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
