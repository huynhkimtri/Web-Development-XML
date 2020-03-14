/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author huynh
 */
public class XMLTextUtils {

    /**
     *
     * @param src
     * @return
     */
    public static String refineHTMLToXML(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        XMLSyntaxChecker checker = new XMLSyntaxChecker();
        src = checker.makeWellformedXMLFromHTML(src);
        src = getBody(src);
        System.out.println(src);
        return src;
    }

    /**
     *
     * @param src
     * @return
     */
    private static String getBody(String src) {
        String result = src;

        String expression = "<body.*?</body>";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    /**
     *
     * @param src
     * @return
     */
    public static String removeMiscellaneousTags(String src) {
        String result = src;

        String exp = "<script.*?</script>";
        result = result.replaceAll(exp, "");

        exp = "<style.*?</style>";
        result = result.replaceAll(exp, "");

        exp = "<!--.*?-->";
        result = result.replaceAll(exp, "");

        exp = "&nbsp;?";
        result = result.replaceAll(exp, "");

        return result;
    }
}
