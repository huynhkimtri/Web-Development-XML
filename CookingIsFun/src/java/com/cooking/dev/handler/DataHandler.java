/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.handler;

import com.cooking.dev.jaxb.Ingredient;
import com.cooking.dev.jaxb.IngredientItem;
import com.cooking.dev.jaxb.Recipe;
import java.math.BigInteger;

/**
 *
 * @author huynh
 */
public class DataHandler {

    public static void handleRecipeData(Recipe r) {
        if (r.getServings().equals("")) {
            r.setServings("2-4");
        }
        if (r.getDescription().equals("")) {
            r.setDescription("Đang cập nhật...");
        }
        if (r.getPrepTime().intValue() == 0) {
            r.setPrepTime(BigInteger.valueOf(15));
        }
        if (r.getCookTime().intValue() == 0) {
            r.setCookTime(BigInteger.valueOf(30));
        }
    }

    public static void handleIngredientData(Ingredient i) {
        if (i.getDescription().equals("")) {
            i.setDescription("Đang cập nhật...");
        }
    }

    public static void handleUnitIngredient(IngredientItem item) {
        String unit = item.getUnit().toLowerCase();
        if (unit.equals("g") || unit.equals("gam") || unit.equals("gr")) {
            unit = "gram";
        }
        if (unit.equals("l")) {
            unit = "lít";
        }
        if (unit.equals("khứa")) {
            unit = "khúc";
        }
        if (unit.equals("m")) {
            unit = "ít";
        }
        item.setUnit(unit);
    }
}
