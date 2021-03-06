/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import com.cooking.dev.service.RecipeService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    /**
     *
     * @param link
     * @param xslDoc
     * @return
     */
    public static InputStream crawlAndTransformXML(String link, String xslDoc) {
        InputStream stream = null;
        try {
            String rawData = crawlDataFromLink(link);
            stream = XMLTextUtils.refineHTMLToXML(rawData);
            if (stream != null) {
                stream = TrAXUtils.transformXML(stream, xslDoc);
            }
        } catch (TransformerException | UnsupportedEncodingException ex) {
            Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stream;
    }

    /**
     * Crawl string data from link
     *
     * @param link
     * @return
     * @throws TransformerException
     */
    public static String crawlDataFromLink(String link) throws TransformerException {
        String result = null;
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setReadTimeout(8 * 1000);
//            connection.setConnectTimeout(8 * 1000);
            InputStream inputStream = connection.getInputStream();
            result = getStringFromStream(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     *
     * @param inputStream
     * @return
     */
    public static String getStringFromStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        InputStreamReader streamReader
                = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
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

    /**
     *
     * @param is
     * @return
     */
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
        } catch (TransformerException | SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
