/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author huynh
 */
public class UnicodeUtils {

    /**
     *
     * @param string
     * @return
     */
    public static String decode(String string) {
        String s = string;
        s = s.replaceAll("&ndash;", "-");
        s = s.replaceAll("&nbsp;", "");
        s = s.replaceAll("&hellip;", "…");
        s = s.replaceAll("&rarr;", "→");
        return s;
    }

    /**
     *
     * @param string
     * @return
     */
    public static String encode(String string) {
//        string = string.replaceAll("&amp;", "&");
        string = string.replaceAll("&quot;", "\"");
        string = string.replaceAll("&Agrave;", "À");
        string = string.replaceAll("&agrave;", "à");
        string = string.replaceAll("&Aacute;", "Á");
        string = string.replaceAll("&aacute;", "á");
        string = string.replaceAll("&Atilde;", "Ã");
        string = string.replaceAll("&atilde;", "ã");
        string = string.replaceAll("&Acirc;", "Â");
        string = string.replaceAll("&acirc;", "â");
        string = string.replaceAll("&Egrave;", "È");
        string = string.replaceAll("&egrave;", "è");
        string = string.replaceAll("&Eacute;", "É");
        string = string.replaceAll("&eacute;", "é");
        string = string.replaceAll("&Ecirc;", "Ê");
        string = string.replaceAll("&ecirc;", "ê");
        string = string.replaceAll("&Iacute;", "Í");
        string = string.replaceAll("&iacute;", "í");
        string = string.replaceAll("&Igrave;", "Ì");
        string = string.replaceAll("&igrave;", "ì");
        string = string.replaceAll("&Ograve;", "Ò");
        string = string.replaceAll("&ograve;", "ò");
        string = string.replaceAll("&Oacute;", "Ó");
        string = string.replaceAll("&oacute;", "ó");
        string = string.replaceAll("&Otilde;", "Õ");
        string = string.replaceAll("&otilde;", "õ");
        string = string.replaceAll("&Ocirc;", "Ô");
        string = string.replaceAll("&ocirc;", "ô");
        string = string.replaceAll("&Uacute;", "Ú");
        string = string.replaceAll("&uacute;", "ú");
        string = string.replaceAll("&Ugrave;", "Ù");
        string = string.replaceAll("&ugrave;", "ù");
        string = string.replaceAll("&Yacute;", "Ý");
        string = string.replaceAll("&yacute;", "ý");
        string = string.replaceAll("&ndash;", "–");
        return string;
    }

    /**
     *
     * @param stream
     * @return
     */
    public static InputStream encode(InputStream stream) {
        String string = CrawlerUtils.getStringFromStream(stream);
        string = encode(string);
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeUrl(String url) throws UnsupportedEncodingException {
        int last = url.lastIndexOf("/") + 1;
        String left = url.substring(0, last);
        String right = url.substring(last);
        return left + URLEncoder.encode(right, "UTF-8");
    }
}
