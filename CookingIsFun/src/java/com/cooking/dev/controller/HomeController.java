/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import com.cooking.dev.dao.RecipeDAO;
import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Domains;
import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.util.JAXBUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    private static final String DOMAINS_FILE = "WEB-INF/config/domains.xml";

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
        String url = "views/home.jsp";
        response.setContentType("text/html;charset=UTF-8");
        RecipeDAO dao = new RecipeDAO();
        dao.findTopUsingProcedure(15);
        List<Recipe> listOfRecipes = dao.getListOfRecipes();
        request.setAttribute("TOP_RECIPES", listOfRecipes);

        ServletContext context = request.getServletContext();
        String contextPath = context.getRealPath("/");
        FileInputStream fis = new FileInputStream(new File(contextPath + DOMAINS_FILE));

        Domains domains = new Domains();
        domains = JAXBUtils.unMarshal(fis, domains);
        List<Domain> listOfDomains = domains.getDomain();
        Domain recipeDomain = listOfDomains.get(0);
        HttpSession session = request.getSession();
        session.setAttribute("RECIPE_DOMAINS", recipeDomain);

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
