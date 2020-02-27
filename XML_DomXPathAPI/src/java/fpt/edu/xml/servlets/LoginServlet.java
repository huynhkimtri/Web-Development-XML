/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.servlets;

import fpt.edu.xml.helpers.XMLHelpers;
import java.io.IOException;
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;

/**
 *
 * @author huynh
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

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
        String path = INVALID_PAGE;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOM_TREE");
            if (doc != null) {
                // xpath expression
                String expression
                        = "//student[@id='"
                        + username
                        + "' and normalize-space(password)='"
                        + password
                        + "' and normalize-space(status)!='dropout']";
                // create xpath object
                XPath xp = XMLHelpers.getXPath();
                // execute xpath
                boolean result = (boolean) xp.evaluate(expression, doc, XPathConstants.BOOLEAN);
                if (result) {
                    path = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    String fullName = "";

                    expression = "//student[@id='" + username + "']/lastname";
                    String temp = (String) xp.evaluate(expression, doc, XPathConstants.STRING);
                    fullName = temp.trim();
                    expression = "//student[@id='" + username + "']/middlename";
                    temp = (String) xp.evaluate(expression, doc, XPathConstants.STRING);
                    fullName = fullName + " " + temp.trim();
                    expression = "//student[@id='" + username + "']/firstname";
                    temp = (String) xp.evaluate(expression, doc, XPathConstants.STRING);
                    fullName = fullName + " " + temp.trim();

                    session.setAttribute("FULLNAME", fullName);
                } else {
                    path = INVALID_PAGE;
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
