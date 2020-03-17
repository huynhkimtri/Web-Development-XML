
package com.cooking.dev.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cooking.dev.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cooking.dev.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Recipes }
     * 
     */
    public Recipes createRecipes() {
        return new Recipes();
    }

    /**
     * Create an instance of {@link Recipe }
     * 
     */
    public Recipe createRecipe() {
        return new Recipe();
    }

    /**
     * Create an instance of {@link IngredientItem }
     * 
     */
    public IngredientItem createIngredientItem() {
        return new IngredientItem();
    }

    /**
     * Create an instance of {@link InstructionItem }
     * 
     */
    public InstructionItem createInstructionItem() {
        return new InstructionItem();
    }

    /**
     * Create an instance of {@link ListCategories }
     * 
     */
    public ListCategories createListCategories() {
        return new ListCategories();
    }

    /**
     * Create an instance of {@link ListIngredients }
     * 
     */
    public ListIngredients createListIngredients() {
        return new ListIngredients();
    }

    /**
     * Create an instance of {@link ListIntructions }
     * 
     */
    public ListIntructions createListIntructions() {
        return new ListIntructions();
    }

}
