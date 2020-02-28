/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.servlets;

import fpt.edu.xml.dtos.StudentDTO;
import fpt.edu.xml.helpers.XMLHelpers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
 * @author huynhkimtri
 */
@WebServlet(name = "SearchAddressServlet", urlPatterns = {"/SearchAddressServlet"})
public class SearchAddressServlet extends HttpServlet {

    private final String RESULT_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = RESULT_PAGE;
        String searchValue = request.getParameter("searchValue");
        try {
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOM_TREE");
            if (doc != null) {
                // xpath expression
                String expression = "//student[contains(address,'" + searchValue + "')]";
                // create xpath object
                XPath xp = XMLHelpers.getXPath();
                // execute xpath
                NodeList studentList = (NodeList) xp.evaluate(expression, doc, XPathConstants.NODESET);
                // process
                if (studentList != null) {
                    List<StudentDTO> listOfStudents = null;
                    for (int i = 0; i < studentList.getLength(); i++) {
                        Node temp = studentList.item(i);
                        String id = XMLHelpers.getValue("@id", temp);
                        String className = XMLHelpers.getValue("@class", temp);
                        String password = XMLHelpers.getValue("password", temp);
                        String lastName = XMLHelpers.getValue("lastname", temp);
                        String middleName = XMLHelpers.getValue("middlename", temp);
                        String firstName = XMLHelpers.getValue("firstname", temp);
                        String sexVal = XMLHelpers.getValue("sex", temp);
                        boolean sex = false;
                        if (sexVal.trim().equals("1")) {
                            sex = true;
                        }
                        String address = XMLHelpers.getValue("address", temp);
                        String status = XMLHelpers.getValue("status", temp);
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
            log(ex.getMessage());
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
