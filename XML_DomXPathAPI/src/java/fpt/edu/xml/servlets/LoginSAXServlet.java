package fpt.edu.xml.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fpt.edu.xml.helpers.StudentHandler;
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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author huynh
 */
@WebServlet(urlPatterns = {"/LoginSAXServlet"})
public class LoginSAXServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";
    private final String XML_FILE = "WEB-INF/studentAccounts.xml";

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            ServletContext context = this.getServletContext();
            String realPath = context.getRealPath("/");
            String xmlFilePath = realPath + XML_FILE;

            StudentHandler handler = new StudentHandler(username, password);
            XMLHelpers.parseFileToSAX(xmlFilePath, handler);

            if (handler.isFound()) {
                HttpSession session = request.getSession();
                session.setAttribute("FULLNAME", handler.getFullName());
                session.setAttribute("USERNAME", username);
            }

        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(LoginSAXServlet.class.getName()).log(Level.SEVERE, null, ex);
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
