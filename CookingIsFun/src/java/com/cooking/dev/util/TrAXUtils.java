/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author huynh
 */
public class TrAXUtils {

    /**
     *
     * @param xmlDoc
     * @param xsltDoc
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws FileNotFoundException
     */
    public static void transformXMLUsingXSLT(String xmlDoc, String xsltDoc)
            throws TransformerConfigurationException, TransformerException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslt = new StreamSource(xsltDoc);
        Transformer transformer = factory.newTransformer(xslt);
        StreamSource xml = new StreamSource(xmlDoc);
        StreamResult result = new StreamResult(new FileOutputStream("src/dev/utils/test.xml"));
        transformer.transform(xml, result);
    }

    /**
     *
     * @param stream
     * @param xslDoc
     * @return
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    public static InputStream transformXML(InputStream stream, String xslDoc)
            throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);
        
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Transformer transformer = transformFactory.newTransformer(new StreamSource(new File(xslDoc)));
        
        transformer.transform(new StreamSource(stream), streamResult);
        
        return new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));
    }
}
