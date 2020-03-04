/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.servlets;

import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author huynh
 */
@WebServlet(name = "StAX_LoginServlet", urlPatterns = {"/StAX_LoginServlet"})
public class StAX_LoginServlet extends HttpServlet {

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
        String path = MyConstants.INVALID_PAGE;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        InputStream stream = null;
        XMLStreamReader reader = null;
        try {
            String xmlFilePath
                    = XMLUtils.getXMLFilePath(this.getServletContext(), MyConstants.STUDENT_XML_FILE);
            stream = new FileInputStream(xmlFilePath);
            reader = XMLUtils.parseXMLFileUsingStAXCursor(stream);
            String fullName = "";
            boolean found = false;
            while (reader.hasNext()) {
                int currentCursor = reader.next();
                if (currentCursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("student")) {
                        String id = reader.getAttributeValue("", "id");
                        if (id.equals(username)) {
                            String last = XMLUtils.getTextElementOfCursor("lastname", reader);
                            String middle = XMLUtils.getTextElementOfCursor("middlename", reader);
                            String first = XMLUtils.getTextElementOfCursor("firstname", reader);
                            fullName = last.trim() + " " + middle.trim() + " " + first.trim();
                            String pwd = XMLUtils.getTextElementOfCursor("password", reader);
                            if (password.equals(pwd.trim())) {
                                String status = XMLUtils.getTextElementOfCursor("status", reader);
                                if (!"dropout".equals(status.trim())) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                } // end if cursor is start element
            } // end if reader is exist
            if (found) {
                path = MyConstants.SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("FULL_NAME", fullName);
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(StAX_LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException ex) {
                    Logger.getLogger(StAX_LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stream != null) {
                stream.close();
            }
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
