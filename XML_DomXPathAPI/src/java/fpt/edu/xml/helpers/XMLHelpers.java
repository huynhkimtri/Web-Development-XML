/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author huynhkimtri
 */
public class XMLHelpers implements Serializable {

    /**
     * Parse DOM from XML file
     *
     * @param XMLPathFile
     * @return Document Object
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document parseDOMFromFile(String XMLPathFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(XMLPathFile);
        return doc;
    }

    /**
     * Get XPath Object
     *
     * @return XPath Object
     */
    public static XPath getXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        return path;
    }

    /**
     * Get value from a node with XPath expression
     *
     * @param xpathExpression
     * @param node
     * @return value string
     * @throws XPathExpressionException
     */
    public static String getValue(String xpathExpression, Node node) throws XPathExpressionException {
        XPath xpath = getXPath();
        String result = (String) xpath.evaluate(xpathExpression, node, XPathConstants.STRING);
        return result;
    }

    /**
     * Transform from DOM tree to file
     *
     * @param node
     * @param xmlFilePath
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public static void transformDOMtoFile(Node node, String xmlFilePath) throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source src = new DOMSource(node);
        File file = new File(xmlFilePath);
        Result result = new StreamResult(file);
        transformer.transform(src, result);
    }

    /**
     *
     * @param xmlFilePath
     * @param handler
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void parseFileToSAX(String xmlFilePath, DefaultHandler handler) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser sax = factory.newSAXParser();
        sax.parse(xmlFilePath, handler);
    }

    /**
     *
     * @param stream
     * @return
     * @throws XMLStreamException
     */
    public static XMLStreamReader parseFileUsingStAXCusor(InputStream stream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);
        return reader;
    }

    public static String getTextElementOfCursor(String elementName, XMLStreamReader reader) throws XMLStreamException {
        if (reader == null) {
            return null;
        }
        if (elementName == null) {
            return null;
        }
        if (elementName.trim().isEmpty()) {
            return null;
        }
        while (reader.hasNext()) {
            int currentCursor = reader.getEventType();
            if (currentCursor == XMLStreamConstants.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (elementName.equals(tagName)) { // we locate start element
                    reader.next(); // locate text of element
                    String result = reader.getText(); // get value
                    reader.nextTag(); // jump to end element
                    return result;
                } // end if reader matches element name
            } // end if cursor point to start element
            reader.next();
        } // end if cursor does not point to null
        return null;
    }
}
