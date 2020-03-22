/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author huynh
 */
public class JAXBUtils {

    /**
     * Marshalling Java class to StringWriter object
     *
     * @param <T>
     * @param t
     * @param writer
     */
    public static <T> void marshallStringWriter(T t, StringWriter writer) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(marshaller, writer);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * UnMarshalling Java class from InputStream
     *
     * @param <T>
     * @param t
     * @param stream
     * @return java class
     */
    public static <T> T unmarshalJavaObject(InputStream stream, T t) {
        if (t == null) {
            return t;
        }
        T result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(stream);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Marshalling Java class to String
     *
     * @param <T>
     * @param t
     * @return java class
     */
    public static <T> String marshallToString(T t) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(marshaller, writer);

            result = writer.toString().trim();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Dynamic generate Java class from XML schema with Java API using XJC
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
            Logger.getLogger(JAXBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
