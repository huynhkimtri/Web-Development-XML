/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.jaxb.helpers;

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
public class XJCGenerateJavaObject {

    /**
     * generate Java Object from XML schema
     *
     * @param packageName
     * @param xsdUri
     */
    private static void generateJavaObject(String packageName, String xsdUri) {
        try {
            String output = "src/java";

            SchemaCompiler compiler = XJC.createSchemaCompiler();
            compiler.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("Error: " + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("FatalError: " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("Warning: " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("Info: " + saxpe.getMessage());
                }
            });

            compiler.forcePackageName(packageName);

            File schema = new File(xsdUri);

            InputSource inputSource = new InputSource(schema.toURI().toString());
            compiler.parseSchema(inputSource);
            S2JJAXBModel model = compiler.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println("Finised!");
        } catch (IOException ex) {
            Logger.getLogger(XJCGenerateJavaObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        String pkg = "fpt.edu.jaxb.obj";
        String accountsUri = "src/java/fpt/edu/jaxb/xsd/accounts.xsd";
        String orderDetailsUri = "src/java/fpt/edu/jaxb/xsd/orderDetails.xsd";
        String ordersUri = "src/java/fpt/edu/jaxb/xsd/orders.xsd";
        generateJavaObject(pkg, accountsUri);
        generateJavaObject(pkg, orderDetailsUri);
        generateJavaObject(pkg, ordersUri);
    }
}
