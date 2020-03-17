
package com.cooking.dev.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for domain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="domain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paths" type="{http://www.cookingisfun.com/XMLSchema/dev}paths"/>
 *         &lt;element name="xslOverview" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xslDetail" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "domain", propOrder = {
    "paths",
    "xslOverview",
    "xslDetail"
})
public class Domain {

    @XmlElement(required = true)
    protected Paths paths;
    @XmlElement(required = true)
    protected String xslOverview;
    @XmlElement(required = true)
    protected String xslDetail;
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
     * Gets the value of the xslOverview property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXslOverview() {
        return xslOverview;
    }

    /**
     * Sets the value of the xslOverview property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXslOverview(String value) {
        this.xslOverview = value;
    }

    /**
     * Gets the value of the xslDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXslDetail() {
        return xslDetail;
    }

    /**
     * Sets the value of the xslDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXslDetail(String value) {
        this.xslDetail = value;
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
