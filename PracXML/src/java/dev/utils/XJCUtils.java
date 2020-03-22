/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author huynh
 */
public class XJCUtils {
    
    public static void main(String[] args) {
        generateJAXBFromXSD("dev.jaxb", "web/newXMLSchema1.xsd");
    }

    /**
     * Generate JAXB Classes from XML Schemas
     *
     * @param pkg
     * @param xsd
     */
    public static void generateJAXBFromXSD(String pkg, String xsd) {
        try {
            String output = "src/java";
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("ERROR: " + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("FATAL_ERROR: " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("WARNING: " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("INFO: " + saxpe.getMessage());
                }
            });

            sc.forcePackageName(pkg);

            File schema = new File(xsd);
            String uri = schema.toURI().toString();

            InputSource inputSource = new InputSource(uri);
            sc.parseSchema(inputSource);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));

            System.out.println("Finished");
        } catch (IOException ex) {
            Logger.getLogger(XJCUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
