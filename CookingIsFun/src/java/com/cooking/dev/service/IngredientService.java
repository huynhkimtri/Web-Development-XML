/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.service;

import com.cooking.dev.dao.IngredientDAO;
import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Ingredient;
import com.cooking.dev.jaxb.Ingredients;
import com.cooking.dev.util.CrawlerUtils;
import com.cooking.dev.util.JAXBUtils;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author huynh
 */
public class IngredientService {

    private final ServletContext context;

    public IngredientService(ServletContext context) {
        this.context = context;
    }

    /**
     *
     * @param domain
     * @param origin
     * @param path
     * @param categoryId
     */
    public void crawlIngredients(Domain domain, String origin, String path, int categoryId) {
        List<Ingredient> listOfIngredient;
        String nextPage = null;
        String link;
        InputStream stream;
        do {
            link = origin + path;
            System.out.println("Crawling... " + link);
            stream = CrawlerUtils.crawlAndTransformXML(link,
                    context.getRealPath("/") + domain.getXslOverview());
            Ingredients ingredients = JAXBUtils.unMarshal(stream, new Ingredients());
            listOfIngredient = ingredients.getIngredient();
            int size = listOfIngredient.size();
            for (int i = 0; i < size; i++) {
                Ingredient tmp = listOfIngredient.get(i);
                link = origin + tmp.getLink();
                stream = CrawlerUtils.crawlAndTransformXML(link,
                        context.getRealPath("/") + domain.getXslDetail());
                Ingredient ingredient = JAXBUtils.unMarshal(stream, new Ingredient());
                ingredient.setId(tmp.getId());
                ingredient.setImage(tmp.getImage());
                ingredient.setLink(link);
                System.out.println(ingredient.toString());
//                IngredientDAO dao = new IngredientDAO();
//                dao.save(ingredient);
            }

            int current = getPageNumber(nextPage);
            try {
                nextPage = ingredients.getNextPage();
            } catch (NullPointerException ex) {
                nextPage = "";
                Logger.getLogger(IngredientService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (nextPage.length() > 0) {
                int next = getPageNumber(nextPage);
                if (next < current) {
                    nextPage = "";
                } else {
                    path = nextPage;
                }
            }

        } while (nextPage.length() > 0);
    }

    private int getPageNumber(String url) {
        try {
            String key = "page=";
            int lastIndex = url.lastIndexOf(key);
            String page = url.substring(lastIndex + key.length(), url.length());
            return Integer.parseInt(page);
        } catch (NullPointerException ex) {
            return 1;
        }
    }
}
