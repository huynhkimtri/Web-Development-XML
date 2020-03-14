
package dev.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recipeDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recipeDomain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paths" type="{http://www.project-xml.com/XMLSchema/dev}paths"/>
 *         &lt;element name="parsers" type="{http://www.project-xml.com/XMLSchema/dev}parsers"/>
 *       &lt;/sequence>
 *       &lt;attribute name="origin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipeDomain", propOrder = {
    "paths",
    "parsers"
})
public class RecipeDomain {

    @XmlElement(required = true)
    protected Paths paths;
    @XmlElement(required = true)
    protected Parsers parsers;
    @XmlAttribute(name = "origin")
    protected String origin;

    /**
     * Gets the value of the paths property.
     * 
     * @return
     *     possible object is
     *     {@link Paths }
     *     
     */
    public Paths getPaths() {
        return paths;
    }

    /**
     * Sets the value of the paths property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paths }
     *     
     */
    public void setPaths(Paths value) {
        this.paths = value;
    }

    /**
     * Gets the value of the parsers property.
     * 
     * @return
     *     possible object is
     *     {@link Parsers }
     *     
     */
    public Parsers getParsers() {
        return parsers;
    }

    /**
     * Sets the value of the parsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parsers }
     *     
     */
    public void setParsers(Parsers value) {
        this.parsers = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

}
