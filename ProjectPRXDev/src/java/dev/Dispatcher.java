/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
public class Dispatcher extends HttpServlet {

    public static final String ERROR_PAGE = "error.jsp";
    public static final String ADMIN_PAGE = "admin.jsp";

    private static final String CRAWL_RECIPE_ACTION = "CrawlRecipe";
    private static final String CRAWL_INGREDIENT_ACTION = "CrawlIngredient";

    public static final String RECIPE_CRAWLER = "RecipeCrawlerController";
    public static final String INGREDIENT_CRAWLER = "IngredientCrawlerController";
    public static final String ADMIN = "AdminServlet";

    private static final String CHARSET_UTF8 = StandardCharsets.UTF_8.name();

    /**
     * Dispatching actions from requests
     *
     * @param action
     * @return
     */
    private String dispatchingAction(String action) {
        String path = null;
        if (action != null) {
            switch (action) {
                case CRAWL_RECIPE_ACTION:
                    path = RECIPE_CRAWLER;
                    break;
                case CRAWL_INGREDIENT_ACTION:
                    path = INGREDIENT_CRAWLER;
                    break;
            }
        }
        return path;
    }

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
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(CHARSET_UTF8);
        request.setCharacterEncoding(CHARSET_UTF8);

        String action = request.getParameter("action");
        String path = ADMIN_PAGE;

        try {
            if (action == null) {

            } else {
                switch (action) {
                    case CRAWL_RECIPE_ACTION:
                        path = RECIPE_CRAWLER;
                        break;
                    case CRAWL_INGREDIENT_ACTION:
                        path = INGREDIENT_CRAWLER;
                        break;
                    case "admin":
                        path = ADMIN;
                        break;
                }
            }
        } catch (Exception e) {
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
