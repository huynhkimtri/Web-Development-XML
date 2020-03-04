/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.listeners;

import dev.utils.MyConstants;
import dev.utils.XMLUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Web application lifecycle listener.
 *
 * @author huynh
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying...");
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        String xmlPath = realPath + MyConstants.STUDENT_XML_FILE;
        try {
            Document doc = (Document) XMLUtils.parseDOMFromXMLFile(xmlPath);
            if (doc != null) {
                context.setAttribute("DOM_TREE", doc);
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Deployed!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
