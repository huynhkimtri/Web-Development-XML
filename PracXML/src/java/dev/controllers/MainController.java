/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.controllers;

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
public class MainController extends HttpServlet {

    private final String LOGIN_ACTION = "login";
    private final String SEARCH_ACTION = "search";
    private final String DELETE_ACTION = "delete";
    private final String UPDATE_ACTION = "update";
    private final String INSERT_ACTION = "insert";
    private final String LOGIN_SERVLET = "DOM_LoginServlet";
//    private final String LOGIN_SERVLET = "SAX_LoginServlet";
//    private final String LOGIN_SERVLET = "StAX_LoginServlet";
    private final String SEARCH_SERVLET = "StAX_SearchServlet";
    private final String DELETE_SERVLET = "StAX_DeleteServlet";
    private final String UPDATE_SERVLET = "StAX_UpdateServlet";
    private final String INSERT_SERVLET = "StAX_InsertServlet";

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
        String path = "";
        try {
            String action = request.getParameter("action");
            if (action == null) {
                // do nothing
            } else {
                switch (action) {
                    case LOGIN_ACTION:
                        path = LOGIN_SERVLET;
                        break;
                    case SEARCH_ACTION:
                        path = SEARCH_SERVLET;
                        break;
                    case DELETE_ACTION:
                        path = DELETE_SERVLET;
                        break;
                    case UPDATE_ACTION:
                        path = UPDATE_SERVLET;
                        break;
                    case INSERT_ACTION:
                        path = INSERT_SERVLET;
                        break;
                    default:
                        break;
                }
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
