package com.cooking.dev.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ingredients complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ingredients">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ingredient" type="{http://www.project-xml.com/XMLSchema/dev}ingredient" maxOccurs="unbounded"/>
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
    "ingredient",
    "nextPage"
})
@XmlRootElement(name = "ingredients")
public class Ingredients {

    @XmlElement(required = true)
    protected List<Ingredient> ingredient;
    protected String nextPage;

    /**
     * Gets the value of the ingredient property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the ingredient property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIngredient().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ingredient }
     *
     *
     * @return
     */
    public List<Ingredient> getIngredient() {
        if (ingredient == null) {
            ingredient = new ArrayList<>();
        }
        return this.ingredient;
    }

    /**
     * Gets the value of the nextPage property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Sets the value of the nextPage property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNextPage(String value) {
        this.nextPage = value;
    }

}
