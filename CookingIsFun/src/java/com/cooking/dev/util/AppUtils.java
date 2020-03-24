/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.service.RecipeService;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author huynh
 */
public class AppUtils {

    public static void main(String[] args) {
//        String a = "com.cooking.dev.jaxb";
//        String b = "web/WEB-INF/xsd/domains.xsd";
//        String c = "web/WEB-INF/xsd/ingredients.xsd";
//        String d = "web/WEB-INF/xsd/recipes.xsd";
//        JAXBUtils.generateJAXBFromXSD(a, b);
//        JAXBUtils.generateJAXBFromXSD(a, c);
//        JAXBUtils.generateJAXBFromXSD(a, d);
//        String s = "http://gl.amthuc365.vn/thumbnails/375/280//uploads/i/Cong_Thuc/2014/1-2015/S??n-tr?u-n??ng-ki?u-Âu.jpg?v\\u003d4.1";
//        System.out.println(s.lastIndexOf("/"));
//        System.out.println(s.substring(s.lastIndexOf("/") + 1));
//        System.out.println(s.substring(0, s.lastIndexOf("/") + 1));
//        System.out.println(s);
        test("http://www.amthuc365.vn", "/cong-thuc/3679-che-sau-rieng-ngon-mat-ngay-he.html");
        test("http://www.amthuc365.vn", "/cong-thuc/4275-cach-lam-mut-dua-non-ngon-deo-nhat.html");
        test("http://www.amthuc365.vn", "/cong-thuc//3385-trung-cut-tam-bot-chien-xu.html");
    }

    private static void test(String origin, String path) {
        String link;
        InputStream stream;
        link = origin + path;
        stream = crawlAndTransformXML(link, "web/WEB-INF/xsl/recipe-detail.xsl");
        boolean result = JAXBUtils.validateXML(stream, "web/WEB-INF/xsd/recipes.xsd");
        System.out.println("isValid=" + result);
        if (result) {
            Recipe recipe = JAXBUtils.unMarshal(stream, new Recipe());
            System.out.println(recipe.toString());
        }
    }

    private static InputStream crawlAndTransformXML(String link, String xslDoc) {
        InputStream stream = null;
        try {
            String rawData = CrawlerUtils.crawlDataFromLink(link);
            stream = XMLTextUtils.refineHTMLToXML(rawData);
            if (stream != null) {
                stream = TrAXUtils.transformXML(stream, xslDoc);
            }
        } catch (TransformerException | UnsupportedEncodingException ex) {
            Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stream;
    }
}
