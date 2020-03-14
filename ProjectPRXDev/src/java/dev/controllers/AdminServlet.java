/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.controllers;

import dev.jaxbs.Path;
import dev.jaxbs.RecipeDomain;
import dev.jaxbs.RecipeDomains;
import dev.utils.DevUtils;
import dev.utils.JAXBUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {
    
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String RECIPE_DOMAINS = "WEB-INF/configs/recipe-domains.xml";

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
//        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String path = ADMIN_PAGE;
        try {
            ServletContext context = request.getServletContext();
            String recipeDomains = DevUtils.getFilePath(context, RECIPE_DOMAINS);
            File file = new File(recipeDomains);
            FileInputStream fis = new FileInputStream(file);
            RecipeDomains domains
                    = JAXBUtils.unmarshallInputStream(new RecipeDomains(), fis);
            List<RecipeDomain> listOfRecipeDomain = domains.getRecipeDomain();
            RecipeDomain domain = listOfRecipeDomain.get(0);
            List<Path> listOfPath = domain.getPaths().getPath();
            listOfPath.forEach((item) -> {
                System.out.println(item.getId());
                System.out.println(item.getLink());
                System.out.println(item.getValue());
            });
        } finally {
            
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
