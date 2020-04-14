/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.controller;

import com.cooking.dev.dao.RecipeDAO;
import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.util.JAXBUtils;
import com.cooking.dev.util.TrAXUtils;
import com.cooking.dev.util.UnicodeUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 *
 * @author huynh
 */
@WebServlet(name = "PrintRecipeController", urlPatterns = {"/PrintRecipeController"})
public class PrintRecipeController extends HttpServlet {

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
        String text = request.getParameter("id");
        int recipeid;
        if (text != null) {
            try {
                String realPath = getServletContext().getRealPath("/");
                String xslPath = realPath + "WEB-INF/config/fop/recipeFo.xsl";
                String foPath = realPath + "WEB-INF/config/fop/recipeFo.fo";
                recipeid = Integer.parseInt(text);
                RecipeDAO dao = new RecipeDAO();
                Recipe recipe = dao.findByIdUsingProcedure(recipeid);
                StringWriter writer = new StringWriter();
                JAXBUtils.marshallStringWriter(recipe, writer);
                String tmp = UnicodeUtils.encode(writer.getBuffer().toString());
                TrAXUtils.transform(xslPath, tmp, foPath, realPath);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                FopFactory ff = FopFactory.newInstance();
                ff.setUserConfig(realPath + "/WEB-INF/config/fop/fop.xml");
                FOUserAgent fua = ff.newFOUserAgent();
                Fop fop = ff.newFop(MimeConstants.MIME_FOP_PRINT, fua, out);
                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                File fo = new File(foPath);
                Source src = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());
                trans.transform(src, result);
                byte[] content = out.toByteArray();
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                response.getOutputStream().flush();
            } catch (TransformerException | SAXException ex) {
                Logger.getLogger(PrintRecipeController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
