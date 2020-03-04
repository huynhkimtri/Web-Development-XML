/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.servlets;

import dev.dtos.StudentDTO;
import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author huynh
 */
@WebServlet(name = "StAX_SearchServlet", urlPatterns = {"/StAX_SearchServlet"})
public class StAX_SearchServlet extends HttpServlet {

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
        String url = MyConstants.SEARCH_PAGE;
        String searchValue = request.getParameter("searchValue");
        InputStream stream = null;
        XMLEventReader reader = null;
        List<StudentDTO> listOfStudents = null;
        try {
            String xmlFilePath
                    = XMLUtils.getXMLFilePath(this.getServletContext(),
                            MyConstants.STUDENT_XML_FILE);
            stream = new FileInputStream(xmlFilePath);
            reader = XMLUtils.parseXMLFileUsingStAXIterator(stream);
            while (reader.hasNext()) {
                reader.nextEvent();
                String id = XMLUtils.getNodeStAXValue(reader, "student", "", "id");
                if (id != null) {
                    String className = XMLUtils.getNodeStAXValue(reader, "student", "", "class");
                    String lastName = XMLUtils.getTextElementStAXContext(reader, "lastname");
                    String middleName = XMLUtils.getTextElementStAXContext(reader, "middlename");
                    String firstName = XMLUtils.getTextElementStAXContext(reader, "firstname");
                    String sexVal = XMLUtils.getTextElementStAXContext(reader, "sex");
                    boolean sex = false;
                    if (sexVal.trim().equals("1")) {
                        sex = true;
                    }
                    String password = XMLUtils.getTextElementStAXContext(reader, "password");
                    String address = XMLUtils.getTextElementStAXContext(reader, "address");
                    String status = XMLUtils.getTextElementStAXContext(reader, "status");
                    if (address.trim().contains(searchValue)) {
                        StudentDTO student
                                = new StudentDTO(id, lastName.trim(), middleName.trim(),
                                        firstName.trim(), password.trim(), className.trim(),
                                        address.trim(), sex, status.trim());
                        if (listOfStudents == null) {
                            listOfStudents = new ArrayList<>();
                        }
                        listOfStudents.add(student);
                    }
                }
            } // end while
            request.setAttribute("SEARCH_RESULTS", listOfStudents);

        } catch (XMLStreamException ex) {
            Logger.getLogger(StAX_SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException ex) {
                    Logger.getLogger(StAX_SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stream != null) {
                stream.close();
            }
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
