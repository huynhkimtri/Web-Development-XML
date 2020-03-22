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
import org.apache.log4j.Logger;

/**
 *
 * @author huynh
 */
public class FrontController extends HttpServlet {

    static final Logger logger = Logger.getLogger(FrontController.class);

//    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String INDEX_PAGE = "views/index.jsp";
    private static final String ADMIN_CONTROLLER = "AdminController";
    private static final String CRAWL_RECIPE_CONTROLLER = "CrawlRecipeController";
    private static final String CRAWL_INGREDIENT_CONTROLLER = "CrawlIngredientController";

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

        String path = INDEX_PAGE;

        try {
            String action = request.getParameter("action");
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
                    default:
                        break;
                }
            } else {
                // do nothing
            }
        } finally {
            logger.info("Dispatching to " + path);
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
