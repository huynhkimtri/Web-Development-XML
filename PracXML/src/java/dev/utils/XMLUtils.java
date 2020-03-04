/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
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
 * @author huynh
 */
public class XMLUtils implements Serializable {

    /**
     *
     * @param context
     * @param xmlFile
     * @return
     */
    public static String getXMLFilePath(ServletContext context, String xmlFile) {
        String realPath = context.getRealPath("/");
        return realPath + xmlFile;
    }

    /**
     *
     * @param xmlFilePath
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Document parseDOMFromXMLFile(String xmlFilePath)
            throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFilePath);
        return doc;
    }

    /**
     *
     * @return
     */
    public static XPath getXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xp = factory.newXPath();
        return xp;
    }

    /**
     *
     * @param xpathExpression
     * @param node
     * @return
     * @throws XPathExpressionException
     */
    public static String getValue(String xpathExpression, Node node)
            throws XPathExpressionException {
        XPath xp = getXPath();
        String result = (String) xp.evaluate(xpathExpression, node, XPathConstants.STRING);
        return result;
    }

    /**
     *
     * @param node
     * @param xmlFilePath
     * @throws TransformerException
     */
    public static void transformDOMtoXMLFile(Node node, String xmlFilePath)
            throws TransformerException {
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
    public static void parseXMLFileToSAX(String xmlFilePath, DefaultHandler handler)
            throws IOException, ParserConfigurationException, SAXException {
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
    public static XMLStreamReader parseXMLFileUsingStAXCursor(InputStream stream)
            throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);
        return reader;
    }

    /**
     *
     * @param stream
     * @return
     * @throws XMLStreamException
     */
    public static XMLEventReader parseXMLFileUsingStAXIterator(InputStream stream)
            throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(stream);
        return reader;
    }

    /**
     *
     * @param elementName
     * @param reader
     * @return
     * @throws XMLStreamException
     */
    public static String getTextElementOfCursor(String elementName, XMLStreamReader reader)
            throws XMLStreamException {
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

    /**
     * Get the text node of an element
     *
     * @param reader
     * @param elementName
     * @return
     * @throws XMLStreamException
     */
    public static String getTextElementStAXContext(XMLEventReader reader,
            String elementName) throws XMLStreamException {
        if (reader == null) {
            return null;
        }
        if (elementName == null || elementName.trim().isEmpty()) {
            return null;
        }
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement start = event.asStartElement();
                if (start.getName().getLocalPart().equals(elementName)) {
                    event = reader.nextEvent();
                    if (event.isCharacters()) {
                        Characters chars = event.asCharacters();
                        String value = chars.getData();
                        reader.nextEvent();
                        return value;
                    }
                } // end if localPart
            } // end if start
        } // end while
        return null;
    }

    /**
     * Get the attribute of a node
     *
     * @param reader
     * @param elementName
     * @param namespaceUri
     * @param attName
     * @return
     * @throws XMLStreamException
     */
    public static String getNodeStAXValue(XMLEventReader reader, String elementName,
            String namespaceUri, String attName) throws XMLStreamException {
        if (reader == null) {
            return null;
        }
        if (elementName == null || elementName.trim().isEmpty()) {
            return null;
        }
        while (reader.hasNext()) {
            XMLEvent event = reader.peek();
            if (event.isStartElement()) {
                StartElement start = event.asStartElement();
                if (start.getName().getLocalPart().equals(elementName)) {
                    QName qName = new QName(namespaceUri, attName);
                    Attribute attribute = start.getAttributeByName(qName);
                    if (attribute != null) {
                        String value = attribute.getValue();
                        return value;
                    }
                } // end if localPart
            } // end if start
            reader.nextEvent();
        } // end while
        return null;
    }
}
