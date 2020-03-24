/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Path;
import com.cooking.dev.service.RecipeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huynh
 */
@WebServlet(name = "CrawlRecipeController", urlPatterns = {"/CrawlRecipeController"})
public class CrawlRecipeController extends HttpServlet {

    private static final String ADMIN_PAGE = "views/admin.jsp";
    private static final String RESULT_PAGE = "views/result.html";

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
        String url = ADMIN_PAGE;
        HttpSession session = request.getSession();
        Domain domain = (Domain) session.getAttribute("RECIPE_DOMAINS");
        String origin = request.getParameter("origin");
        // get the link of the first path => get all
//        String path = domain.getPaths().getPath().get(0).getLink();

        RecipeService service = new RecipeService(request.getServletContext());
        List<Path> listOfPaths = domain.getPaths().getPath();
//        List<Recipe> listOfRecipe;

        if (listOfPaths != null) {
            int sizePaths = listOfPaths.size();
            String path;
            for (int i = 0; i < sizePaths; i++) {
                path = listOfPaths.get(i).getLink();
                int cateId = listOfPaths.get(i).getId().intValue();
                service.crawlRecipesTest(domain, origin, path, cateId);
            }
//            for (int i = 0; i < sizePaths; i++) {
//                path = listOfPaths.get(i).getLink();
//                service.crawlRecipesTest(domain, origin, path);
//            }
            url = RESULT_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
