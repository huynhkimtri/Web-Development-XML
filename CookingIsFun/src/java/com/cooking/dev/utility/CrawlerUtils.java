/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CrawlerUtils {

    public static String crawlDataFromLink(String link) throws TransformerException {
        String result = null;
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            connection.setReadTimeout(8 * 1000);
            connection.setConnectTimeout(8 * 1000);
            InputStream is = connection.getInputStream();
            result = getString(is);
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String getString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (TransformerException | SAXException | IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
