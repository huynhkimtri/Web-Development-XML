
package dev.jaxbs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dev.jaxbs package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dev.jaxbs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecipeDomains }
     * 
     * @return 
     */
    public RecipeDomains createRecipeDomains() {
        return new RecipeDomains();
    }

    /**
     * Create an instance of {@link RecipeDomain }
     * 
     * @return 
     */
    public RecipeDomain createRecipeDomain() {
        return new RecipeDomain();
    }

    /**
     * Create an instance of {@link Path }
     * 
     * @return 
     */
    public Path createPath() {
        return new Path();
    }

    /**
     * Create an instance of {@link Parser }
     * 
     * @return 
     */
    public Parser createParser() {
        return new Parser();
    }

    /**
     * Create an instance of {@link Paths }
     * 
     * @return 
     */
    public Paths createPaths() {
        return new Paths();
    }

    /**
     * Create an instance of {@link Parsers }
     * 
     * @return 
     */
    public Parsers createParsers() {
        return new Parsers();
    }

}
