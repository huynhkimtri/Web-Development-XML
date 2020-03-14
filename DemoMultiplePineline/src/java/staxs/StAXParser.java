/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxs;

import com.sun.xml.internal.stream.events.CharacterEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import jaxbs.BookBean;

/**
 *
 * @author huynh
 */
public class StAXParser {

    public static BookBean readData(String fileName) {
        try {
            BookBean book = new BookBean();
            XMLInputFactory factory = XMLInputFactory.newFactory();
            FileInputStream inputStream = new FileInputStream(fileName);
            XMLEventReader reader = factory.createXMLEventReader(inputStream);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement start = event.asStartElement();
                    String localName = start.getName().getLocalPart();
                    Characters chars;

                    switch (localName) {
                        case "name":
                            event = reader.nextEvent();
                            chars = event.asCharacters();
                            book.setName(chars.getData());
                            break;
                        case "author":
                            event = reader.nextEvent();
                            chars = event.asCharacters();
                            book.setAuthor(chars.getData());
                            break;
                        case "price":
                            event = reader.nextEvent();
                            chars = event.asCharacters();
                            int price = Integer.parseInt(chars.getData());
                            book.setPrice(price);
                            break;
                    }
                }
            }
            return book;
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
