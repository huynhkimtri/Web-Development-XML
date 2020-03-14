
package dev.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beginParseSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endParseSign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stylesheet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parser", propOrder = {
    "beginParseSign",
    "endParseSign",
    "stylesheet"
})
public class Parser {

    @XmlElement(required = true)
    protected String beginParseSign;
    @XmlElement(required = true)
    protected String endParseSign;
    @XmlElement(required = true)
    protected String stylesheet;

    /**
     * Gets the value of the beginParseSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginParseSign() {
        return beginParseSign;
    }

    /**
     * Sets the value of the beginParseSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginParseSign(String value) {
        this.beginParseSign = value;
    }

    /**
     * Gets the value of the endParseSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndParseSign() {
        return endParseSign;
    }

    /**
     * Sets the value of the endParseSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndParseSign(String value) {
        this.endParseSign = value;
    }

    /**
     * Gets the value of the stylesheet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStylesheet() {
        return stylesheet;
    }

    /**
     * Sets the value of the stylesheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStylesheet(String value) {
        this.stylesheet = value;
    }

}
