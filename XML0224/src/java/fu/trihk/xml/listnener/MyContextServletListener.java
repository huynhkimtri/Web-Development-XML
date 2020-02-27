/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.trihk.xml.listnener;

import fu.trihk.xml.utils.XMLHepler;
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

    private final String XML_FILE = "WEB-INF/studentAccounts.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying...");
        // find xml file path
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        String xmlFilePath = realPath + XML_FILE;
        try {
            // parse dom
            Document doc = XMLHepler.parseDOMFromFile(xmlFilePath);
            if (doc != null) {
                context.setAttribute("DOM_TREE", doc);
            }
            System.out.println("Deployed!");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
