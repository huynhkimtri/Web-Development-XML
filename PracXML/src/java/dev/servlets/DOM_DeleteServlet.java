/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.servlets;

import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author huynh
 */
@WebServlet(name = "DOM_DeleteServlet", urlPatterns = {"/DOM_DeleteServlet"})
public class DOM_DeleteServlet extends HttpServlet {

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
        String path = MyConstants.SEARCH_PAGE;
        String studentId = request.getParameter("id");
        String lastSearchValue = request.getParameter("lastSearchValue");
        try {
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOM_TREE");
            if (doc != null) {
                // find destroyed node xpath expression
                String expression = "//student[@id='" + studentId + "']";
                // xpath object
                XPath xpath = XMLUtils.getXPath();
                // execute path
                Node student = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
                if (student != null) {
                    // delete 
                    // 1. find parent
                    Node parent = student.getParentNode();
                    if (parent != null) {
                        // 2. parent remove student
                        parent.removeChild(student);
                        // 3. stored
                        String realPath = context.getRealPath("/");
                        String xmlFilePath = realPath + MyConstants.STUDENT_XML_FILE;
                        XMLUtils.transformDOMtoXMLFile(parent, xmlFilePath);

                        path = "MainController?action=search&searchValue=" + lastSearchValue;
                    }
                }
            }
        } catch (XPathExpressionException | TransformerException ex) {
            Logger.getLogger(DOM_DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(path);
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
