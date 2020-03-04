/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.servlets;

import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author huynh
 */
@WebServlet(name = "DOM_UpdateServlet", urlPatterns = {"/DOM_UpdateServlet"})
public class DOM_UpdateServlet extends HttpServlet {

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

        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String className = request.getParameter("classname");
        String lastSearchValue = request.getParameter("lastSearchValue");

        String realPath = getServletContext().getRealPath("/");
        String filePath = realPath + MyConstants.STUDENT_XML_FILE;
        try {
            Document doc = XMLUtils.parseDOMFromXMLFile(filePath);
            if (doc != null) {
                XPath xpath = XMLUtils.getXPath();
                String expression = "//student[@id='" + id + "']";
                Node student = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
                if (student != null) {
                    NamedNodeMap attrs = student.getAttributes();
                    attrs.getNamedItem("class").setNodeValue(className);
                    String expStatus = "//student[@id='" + id + "']/status";
                    Node s = (Node) xpath.evaluate(expStatus, doc, XPathConstants.NODE);
                    if (s != null) {
                        s.setTextContent(status);
                    }

                    XMLUtils.transformDOMtoXMLFile(doc, filePath);

                    String urlRedirect = "MainController?action=search&searchValue=" + lastSearchValue;
                    response.sendRedirect(urlRedirect);
                }
            }
        } catch (SAXException | ParserConfigurationException | XPathExpressionException | TransformerException ex) {
            Logger.getLogger(DOM_UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
