/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import com.cooking.dev.handler.DataHandler;
import com.cooking.dev.jaxb.Ingredient;
import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.service.RecipeService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
//        test("http://www.amthuc365.vn", "/cong-thuc/4064-lam-nem-hai-san-sot-mayonnaise.html");
//        test("http://www.amthuc365.vn", "/cong-thuc/4275-cach-lam-mut-dua-non-ngon-deo-nhat.html");
//        test("http://www.amthuc365.vn", "/cong-thuc//3385-trung-cut-tam-bot-chien-xu.html");
//        String nextPage = "/collections/rau-huu-co?page=3";
//        System.out.println("NextPage " + nextPage);
//        int lastIndex = nextPage.lastIndexOf("=");
//        System.out.println("length=" + nextPage.length());
//        System.out.println("lastIndex=" + lastIndex);
//        String page = nextPage.substring(lastIndex + 1, nextPage.length());
//        System.out.println("Page=" + Integer.parseInt(page));
        test2("https://happytrade.org", "/products/thit-vai-heo-sach-an-moc");
        test2("https://happytrade.org", "/products/thit-bap-heo-rut-xuong-huu-co-ausfarm");
        test2("https://happytrade.org", "/products/suon-bo-sach");
    }

    private static ByteArrayOutputStream cloneInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bs = new byte[1024];
        int length;
        while ((length = inputStream.read(bs)) > 1) {
            baos.write(bs, 0, length);
        }
        baos.flush();
        return baos;
    }

    private static void test(String origin, String path) {
        String link = origin + path;
//        InputStream stream = crawlAndTransformXML(link, "web/WEB-INF/xsl/recipe-detail.xsl");
        InputStream st = crawlAndTransformXML(link, "web/WEB-INF/xsl/recipe-detail.xsl");
//        DOMUtils.parseXML(stream);
//        JAXBUtils.validateXML(stream, "web/WEB-INF/xsd/recipe.xsd");
        Recipe recipe = JAXBUtils.unMarshal(st, new Recipe());
        DataHandler.handleRecipeData(recipe);
//            System.out.println("isValid=" + result);
        System.out.println(recipe.toString());
    }

    private static void test2(String origin, String path) {
        String link = origin + path;
        InputStream st = crawlAndTransformXML(link, "web/WEB-INF/xsl/ingredient-happytrade-detail.xsl");
        Ingredient ingredient = JAXBUtils.unMarshal(st, new Ingredient());
        System.out.println(ingredient.toString());
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
