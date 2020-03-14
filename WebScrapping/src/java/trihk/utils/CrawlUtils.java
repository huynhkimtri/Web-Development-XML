/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huynh
 */
public class CrawlUtils implements Serializable {
    
    private static void getDataFromWeb(String webUrl) {
        try {
            URL url = new URL(webUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            StringBuilder sb = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            try (BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line.trim()).append("\n");
                }
            }
            String content = sb.toString().trim();
            System.out.println("-- Start document! --");
            System.out.println(content);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrawlUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("-- End document! --");
        }
    }

    public static void main(String[] args) {
        getDataFromWeb("https://thucphamtantai.com/danh-muc-thuc-pham/thuc-pham-nong-san/");
    }
    
}
