/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.servlets;

import dev.dtos.StudentDTO;
import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author huynh
 */
@WebServlet(name = "DOM_SearchServlet", urlPatterns = {"/DOM_SearchServlet"})
public class DOM_SearchServlet extends HttpServlet {

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
        String keyword = request.getParameter("searchValue");
        String url = MyConstants.SEARCH_PAGE;
        try {
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOM_TREE");
            if (doc != null) {
                // xpath expression
                String expression = "//student[contains(address,'" + keyword + "')]";
                // create xpaht object 
                XPath xp = XMLUtils.getXPath();
                // execute xpaht
                NodeList results = (NodeList) xp.evaluate(expression, doc, XPathConstants.NODESET);
                if (results != null) {
                    List<StudentDTO> listOfStudents = null;
                    for (int i = 0; i < results.getLength(); i++) {
                        Node node = results.item(i);
                        String id = XMLUtils.getValue("@id", node);
                        String className = XMLUtils.getValue("@class", node);
                        String password = XMLUtils.getValue("password", node);
                        String lastName = XMLUtils.getValue("lastname", node);
                        String middleName = XMLUtils.getValue("middlename", node);
                        String firstName = XMLUtils.getValue("firstname", node);
                        String sexVal = XMLUtils.getValue("sex", node);
                        boolean sex = false;
                        if (sexVal.trim().equals("1")) {
                            sex = true;
                        }
                        String address = XMLUtils.getValue("address", node);
                        String status = XMLUtils.getValue("status", node);
                        StudentDTO student
                                = new StudentDTO(id, lastName, middleName,
                                        firstName, password, className, address, sex, status);
                        if (listOfStudents == null) {
                            listOfStudents = new ArrayList<>();
                        }
                        listOfStudents.add(student);
                        request.setAttribute("SEARCH_RESULT", listOfStudents);
                    }
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DOM_SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
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
