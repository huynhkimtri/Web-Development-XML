/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author huynh
 */
public class DevUtils {

    public static void main(String[] args) {
//        String packageName = "dev.jaxbs";
//        String recipeXsd = "web/WEB-INF/schemas/recipes.xsd";
//        String recipeDomainXsd = "web/WEB-INF/schemas/recipe-domains.xsd";
//        XJCUtils.generateJAXBFromXSD(packageName, recipeXsd);
//        XJCUtils.generateJAXBFromXSD(packageName, recipeDomainXsd);
        String src = "http://www.amthuc365.vn/cong-thuc/105-thanh-phan/";
        try {
            testWellformed(src);
        } catch (TransformerException ex) {
            Logger.getLogger(DevUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testWellformed(String urlString) throws TransformerException {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setReadTimeout(8 * 1000);
            connection.setConnectTimeout(8 * 1000);
            String textContent = getString(connection.getInputStream());

            textContent = TextUtils.refineHTMLToXML(textContent);
            InputStream is = new ByteArrayInputStream(textContent.getBytes(StandardCharsets.UTF_8));
            String recipeDomainXsd = "web/WEB-INF/stylesheets/recipe-overview.xsl";
            is = TrAXUtils.transformXML(is, recipeDomainXsd);
            if (checkWellformedXML(is)) {
                System.out.println(urlString + " - well-formed!");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DevUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DevUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get file path
     *
     * @param context
     * @param xmlFile
     * @return
     */
    public static String getFilePath(ServletContext context, String xmlFile) {
        String realPath = context.getRealPath("/");
        return realPath + xmlFile;
    }

    private static String getString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        boolean isInside = false;
        try {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(DevUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder.toString().trim();
    }

    private static boolean checkWellformedXML(InputStream is) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(false);
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getMessage());
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getMessage());
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getMessage());
                }
            });
            Document doc
                    = builder.parse(is);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            StreamResult result = new StreamResult(new File("test.xml"));
            DOMSource source = new DOMSource(doc);

            transformer.transform(source, result);

            System.out.println("Finished!");
            return true;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(DevUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
