
package com.cooking.dev.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recipe" type="{http://www.cookingisfun.com/XMLSchema/dev}recipe" maxOccurs="unbounded"/>
 *         &lt;element name="nextPage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "recipe",
    "nextPage"
})
@XmlRootElement(name = "recipes")
public class Recipes {

    @XmlElement(required = true)
    protected List<Recipe> recipe;
    protected String nextPage;

    /**
     * Gets the value of the recipe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Recipe }
     * 
     * 
     */
    public List<Recipe> getRecipe() {
        if (recipe == null) {
            recipe = new ArrayList<Recipe>();
        }
        return this.recipe;
    }

    /**
     * Gets the value of the nextPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Sets the value of the nextPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextPage(String value) {
        this.nextPage = value;
    }

}
