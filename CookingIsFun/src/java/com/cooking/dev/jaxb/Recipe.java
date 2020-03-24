package com.cooking.dev.jaxb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigInteger;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for recipe complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="recipe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listCategories" type="{http://www.cookingisfun.com/XMLSchema/dev}listCategories" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prepTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cookTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listIngredients" type="{http://www.cookingisfun.com/XMLSchema/dev}listIngredients" minOccurs="0"/>
 *         &lt;element name="listIntructions" type="{http://www.cookingisfun.com/XMLSchema/dev}listIntructions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipe", propOrder = {
    "name",
    "link",
    "image",
    "listCategories",
    "description",
    "servings",
    "prepTime",
    "cookTime",
    "listIngredients",
    "listIntructions"
})
@XmlRootElement(name = "recipe")
public class Recipe {

    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    protected String name;
    protected String link;
    protected String image;
    protected ListCategories listCategories;
    protected String description;
    protected String servings;
    protected BigInteger prepTime;
    protected BigInteger cookTime;
    protected ListIngredients listIngredients;
    protected ListIntructions listIntructions;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the link property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the image property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the listCategories property.
     *
     * @return possible object is {@link ListCategories }
     *
     */
    public ListCategories getListCategories() {
        return listCategories;
    }

    /**
     * Sets the value of the listCategories property.
     *
     * @param value allowed object is {@link ListCategories }
     *
     */
    public void setListCategories(ListCategories value) {
        this.listCategories = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the servings property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getServings() {
        return servings;
    }

    /**
     * Sets the value of the servings property.
     *
     * @param value allowed object is {@link BigInteger }
     *
     */
    public void setServings(String value) {
        this.servings = value;
    }

    /**
     * Gets the value of the prepTime property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getPrepTime() {
        return prepTime;
    }

    /**
     * Sets the value of the prepTime property.
     *
     * @param value allowed object is {@link BigInteger }
     *
     */
    public void setPrepTime(BigInteger value) {
        this.prepTime = value;
    }

    /**
     * Gets the value of the cookTime property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getCookTime() {
        return cookTime;
    }

    /**
     * Sets the value of the cookTime property.
     *
     * @param value allowed object is {@link BigInteger }
     *
     */
    public void setCookTime(BigInteger value) {
        this.cookTime = value;
    }

    /**
     * Gets the value of the listIngredients property.
     *
     * @return possible object is {@link ListIngredients }
     *
     */
    public ListIngredients getListIngredients() {
        return listIngredients;
    }

    /**
     * Sets the value of the listIngredients property.
     *
     * @param value allowed object is {@link ListIngredients }
     *
     */
    public void setListIngredients(ListIngredients value) {
        this.listIngredients = value;
    }

    /**
     * Gets the value of the listIntructions property.
     *
     * @return possible object is {@link ListIntructions }
     *
     */
    public ListIntructions getListIntructions() {
        return listIntructions;
    }

    /**
     * Sets the value of the listIntructions property.
     *
     * @param value allowed object is {@link ListIntructions }
     *
     */
    public void setListIntructions(ListIntructions value) {
        this.listIntructions = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link BigInteger }
     *
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
