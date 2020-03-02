/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.xml.helpers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author huynh
 */
public class StudentHandler extends DefaultHandler {

    private String username;
    private String password;
    private String fullName;
    private String currentTagName;
    private boolean foundUsername;
    private boolean foundPassword;
    private boolean found;

    public StudentHandler() {
        this.foundUsername = false;
        this.foundPassword = false;
        this.found = false;
        this.currentTagName = "";
        this.fullName = "";
    }

    public StudentHandler(String username, String password) {
        this.username = username;
        this.password = password;
        this.foundUsername = false;
        this.foundPassword = false;
        this.found = false;
        this.currentTagName = "";
        this.fullName = "";
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        this.currentTagName = qName;
        if (qName.equals("student")) {
            String id = attributes.getValue("id");
            if (id.equals(username)) {
                this.foundUsername = true;
            } // usernamme matched id
        } // end if qName is student
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        this.currentTagName = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!this.found) {

            if (this.foundUsername) {
                String str = new String(ch, start, length);
                switch (this.currentTagName) {
                    case "lastname":
                        this.fullName = str.trim();
                        break;
                    case "middlename":
                        this.fullName = this.fullName + " " + str.trim();
                        break;
                    case "firstname":
                        this.fullName = this.fullName + " " + str.trim();
                        break;
                    case "password":
                        this.foundUsername = false;
                        if (str.trim().equals(password)) {
                            this.foundPassword = true;
                        } // end if password is correct
                        break;
                    default:
                        break;
                }
            } // end if username is correct
            if (this.foundPassword) {
                if (this.currentTagName.equals("status")) {
                    this.foundPassword = false;
                    String str = new String(ch, start, length);
                    if (!"dropout".equals(str.trim())) {
                        this.found = true;
                    } // end if not equal
                }
            } // end if found password
        } // end if found
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isFound() {
        return found;
    }

}
