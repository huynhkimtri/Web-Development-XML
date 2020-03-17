/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import com.cooking.dev.dao.Dao;
import com.cooking.dev.dao.impl.RecipeCategoryDaoImpl;
import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Domains;
import com.cooking.dev.jaxb.Path;
import com.cooking.dev.utility.JAXBUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author huynh
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        String path = "views/admin.jsp";
        try {
            ServletContext context = request.getServletContext();
            String contextPath = context.getRealPath("/");
            FileInputStream fis = new FileInputStream(new File(contextPath + DOMAINS_FILE));

            Domains domains = new Domains();
            domains = JAXBUtils.unmarshalJavaObject(fis, domains);
            List<Domain> listOfDomains = domains.getDomain();
            Domain recipeDomain = listOfDomains.get(0);
            Domain ingredientDomain = listOfDomains.get(1);

            Dao dao = new RecipeCategoryDaoImpl();
            List<Path> listOfPaths = recipeDomain.getPaths().getPath();
            listOfPaths.forEach((item) -> {
                dao.save(item);
            });

            HttpSession session = request.getSession();
            session.setAttribute("RECIPE_DOMAINS", recipeDomain);
            session.setAttribute("INGREDIENT_DOMAINS", ingredientDomain);

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
