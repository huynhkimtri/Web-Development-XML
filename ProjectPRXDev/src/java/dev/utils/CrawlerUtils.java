/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.utils;

import com.sun.xml.internal.stream.events.EndElementEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author huynh
 */
public class CrawlerUtils implements Serializable {

    /**
     * Convert an InputStream object to String object
     *
     * @param inputStream
     * @return
     */
    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;
        String result = null;
        try {
            streamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(streamReader);
            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                builder.append(inputLine.trim()).append("\n");
            }
            result = builder.toString();
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (streamReader != null) {
                streamReader.close();
            }
        }
        return result;
    }

    /**
     * Get content from web site with URL
     *
     * @param webUrl
     * @return
     */
    private static InputStream getDataFromWebsite(String webUrl)
            throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        ByteArrayInputStream stream = null;
        try {
            URL url = new URL(webUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            String data = convertInputStreamToString(inputStream);
            stream = new ByteArrayInputStream(data.getBytes());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return stream;
    }

    /**
     * Automatically insert the closing tag
     *
     * @param reader
     * @return
     */
    private static StringBuilder autoInsertClosingTag(XMLEventReader reader) {
        StringBuilder builder = new StringBuilder();
        int closingTagMarker = 0;
        while (closingTagMarker >= 0) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException ex) { // catching well-form error
                String msg = ex.getMessage();
                String msgError = "The element type \n";
                if (msg.contains(msgError)) {
                    // cut error message to get the missing tag name
                    int beginIndex = msg.indexOf(msgError) + msgError.length();
                    int endIndex = msg.indexOf("\n must be terminated");
                    String missingTagName = msg.substring(beginIndex, endIndex);
                    if (!missingTagName.isEmpty()) {
                        // initialize the corresponding closing tag
                        QName missingTag = new QName(missingTagName);
                        EndElement end = new EndElementEvent(missingTag);
                        event = end;
                    }
                }
            } catch (NullPointerException ex) { // stop when the end of the document
                break;
            }
            // read the XMLEvent and add it to the listOfEvents
            if (event != null) {
                if (event.isStartElement()) {
                    closingTagMarker += 1;
                } else if (event.isEndElement()) {
                    closingTagMarker -= 1;
                }
                if (closingTagMarker >= 0) {
                    builder.append(event.toString());
                }
            }
        }
        // return the result document has been well-formed
        return builder;
    }

    /**
     * Make well-formed document
     *
     * @param stream
     * @return
     */
    private static InputStream makeWellFormedDocument(InputStream stream) {
        ByteArrayInputStream newStream = null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            XMLEventReader reader = factory.createXMLEventReader(stream);
            StringBuilder wellFormedDoc = autoInsertClosingTag(reader);
            newStream = new ByteArrayInputStream(wellFormedDoc.toString().getBytes());
            reader.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newStream;
    }

    /**
     * Crawl data from link web site
     *
     * @param link
     * @return
     */
    public static InputStream crawlDataFromLink(String link) {
        InputStream stream = null;
        try {
            stream = makeWellFormedDocument(getDataFromWebsite(link));
        } catch (IOException ex) {
            Logger.getLogger(CrawlerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stream;
    }
}
