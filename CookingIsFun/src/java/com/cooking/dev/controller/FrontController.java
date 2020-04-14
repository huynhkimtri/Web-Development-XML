/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
public class FrontController extends HttpServlet {

    private static final String ADMIN_CONTROLLER = "AdminController";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String CRAWL_RECIPE_CONTROLLER = "CrawlRecipeController";
    private static final String RECIPE_DETAIL_CONTROLLER = "RecipeDetailController";
    private static final String CRAWL_INGREDIENT_CONTROLLER = "CrawlIngredientController";
    private static final String RECIPE_SEARCH_BASIC_CONTROLLER = "RecipeSearchBasicController";
    private static final String RECIPE_SEARCH_ADVANCE_CONTROLLER = "RecipeSearchAdvanceController";
    private static final String INGREDIENT_SEARCH_CONTROLLER = "IngredientSearchController";
    private static final String PRINT_RECIPE_CONTROLLER = "PrintRecipeController";
    private static final String INGREDIENT_VIEWER = "IngredientInfoController";
    private static final String INGREDIENT_SEARCH_PAGE = "views/ingredient-search.jsp";
    private static final String RECIPE_SEARCH_PAGE = "views/recipe-search.jsp";
    private static final String RECIPE_INGREDIENT_SEARCH_PAGE = "views/recipe-search-ing.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html;charset=UTF-8");
        String path = HOME_CONTROLLER;

        try {
            String action = request.getParameter("act");
            if (action != null) {
                switch (action) {
                    case "admin":
                        path = ADMIN_CONTROLLER;
                        break;
                    case "crawlRecipe":
                        path = CRAWL_RECIPE_CONTROLLER;
                        break;
                    case "crawlIngredient":
                        path = CRAWL_INGREDIENT_CONTROLLER;
                        break;
                    case "recipeDetail":
                        path = RECIPE_DETAIL_CONTROLLER;
                        break;
                    case "search":
                        path = RECIPE_SEARCH_BASIC_CONTROLLER;
                        break;
                    case "AdvanceSearch":
                        path = RECIPE_SEARCH_ADVANCE_CONTROLLER;
                        break;
                    case "lookup":
                        path = INGREDIENT_VIEWER;
                        break;
                    case "ingPage":
                        path = INGREDIENT_SEARCH_PAGE;
                        break;
                    case "recPage":
                        path = RECIPE_SEARCH_PAGE;
                        break;
                    case "recIngPage":
                        path = RECIPE_INGREDIENT_SEARCH_PAGE;
                        break;
                    case "ingSearch":
                        path = INGREDIENT_SEARCH_CONTROLLER;
                        break;
                    case "print":
                        path = PRINT_RECIPE_CONTROLLER;
                        break;
                    default:
                        break;
                }
            } else {
                // home 
            }
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
