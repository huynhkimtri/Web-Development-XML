
package dev.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parsers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parsers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overview" type="{http://www.project-xml.com/XMLSchema/dev}parser"/>
 *         &lt;element name="detail" type="{http://www.project-xml.com/XMLSchema/dev}parser"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parsers", propOrder = {
    "overview",
    "detail"
})
public class Parsers {

    @XmlElement(required = true)
    protected Parser overview;
    @XmlElement(required = true)
    protected Parser detail;

    /**
     * Gets the value of the overview property.
     * 
     * @return
     *     possible object is
     *     {@link Parser }
     *     
     */
    public Parser getOverview() {
        return overview;
    }

    /**
     * Sets the value of the overview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parser }
     *     
     */
    public void setOverview(Parser value) {
        this.overview = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link Parser }
     *     
     */
    public Parser getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parser }
     *     
     */
    public void setDetail(Parser value) {
        this.detail = value;
    }

}
