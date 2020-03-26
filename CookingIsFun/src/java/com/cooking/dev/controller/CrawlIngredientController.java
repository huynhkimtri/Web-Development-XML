/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Path;
import com.cooking.dev.service.IngredientService;
import com.cooking.dev.service.RecipeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "CrawlIngredientController", urlPatterns = {"/CrawlIngredientController"})
public class CrawlIngredientController extends HttpServlet {

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
        HttpSession session = request.getSession();
        Domain domain = (Domain) session.getAttribute("INGREDIENT_DOMAINS");
        String origin = request.getParameter("origin");

        IngredientService service = new IngredientService(request.getServletContext());
        List<Path> listOfPaths = domain.getPaths().getPath();
        try (PrintWriter out = response.getWriter()) {
            if (listOfPaths != null) {
                int sizePaths = listOfPaths.size();
                String path;
                String value;
                for (int i = 1; i < sizePaths; i++) {
                    Path p = listOfPaths.get(i);
                    path = p.getLink();
                    value = p.getValue();
                    int cateId = listOfPaths.get(i).getId().intValue();
                    service.crawlIngredients(domain, origin, path, cateId);
                    String tmp = origin + path;
                    out.println("<p><strong>" + value + "</strong><br><a target='_blank' href='"
                            + tmp + "'>" + tmp + "</a></p>");
                }
            }
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
