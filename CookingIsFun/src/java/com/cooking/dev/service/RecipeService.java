/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.service;

import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.ListOfRecipes;
import com.cooking.dev.jaxb.RecipeItem;
import com.cooking.dev.utility.CrawlerUtils;
import com.cooking.dev.utility.JAXBUtils;
import com.cooking.dev.utility.TrAXUtils;
import com.cooking.dev.utility.XMLTextUtils;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author huynh
 */
public class RecipeService implements Serializable {

    private final ServletContext context;

    public RecipeService(ServletContext context) {
        this.context = context;
    }

    public List<RecipeItem> crawlRecipes(Domain domain, String origin, String path, int categoryId) {
        List<RecipeItem> listOfRecipeItems = null;
        String nextPage = "";
        String link = origin + domain.getPaths().getLink() + path;
        do {
            System.out.println("Link: " + link);
            // crawl data
            String rawData = CrawlerUtils.crawlDataFromLink(link);
            // refine data
            InputStream stream = XMLTextUtils.refineHTMLToXML(rawData);
            if (stream != null) {
                String xslOverview = context.getRealPath("/") + domain.getXslOverview();
                stream = TrAXUtils.transformXML(stream, xslOverview);
                ListOfRecipes recipes = new ListOfRecipes();
                recipes = JAXBUtils.unmarshalJavaObject(stream, recipes);
                listOfRecipeItems = recipes.getRecipe();
                for (RecipeItem item : listOfRecipeItems) {

                }
                nextPage = recipes.getNext();
            }

            if (nextPage.length() > 0) {
                link = nextPage;
            }
        } while (nextPage.length() > 0);
        return listOfRecipeItems;
    }

}
