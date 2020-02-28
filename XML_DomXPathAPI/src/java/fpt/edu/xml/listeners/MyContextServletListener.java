/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.listeners;

import fpt.edu.xml.helpers.XMLHelpers;
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
 * @author huynhkimtri
 */
public class MyContextServletListener implements ServletContextListener {

    private final String XML_FILE = "WEB-INF/studentAccounts.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger(MyContextServletListener.class.getName()).log(Level.INFO, "Deploying...");
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        String xmlPath = realPath + XML_FILE;
        try {
            Document doc = XMLHelpers.parseDOMFromFile(xmlPath);
            if (doc != null) {
                context.setAttribute("DOM_TREE", doc);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(MyContextServletListener.class.getName()).log(Level.INFO, "Deployed!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
