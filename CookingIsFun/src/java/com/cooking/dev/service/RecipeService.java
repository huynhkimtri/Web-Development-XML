/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.service;

import com.cooking.dev.dao.CategoryDAO;
import com.cooking.dev.dao.CategoryRecipeDAO;
import com.cooking.dev.dao.RecipeDAO;
import com.cooking.dev.dto.CategoryRecipeDTO;
import com.cooking.dev.handler.DataHandler;
import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.Recipe;
import com.cooking.dev.jaxb.Recipes;
import com.cooking.dev.util.CrawlerUtils;
import com.cooking.dev.util.JAXBUtils;
import com.cooking.dev.util.TrAXUtils;
import com.cooking.dev.util.XMLTextUtils;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.transform.TransformerException;

/**
 *
 * @author huynh
 */
public class RecipeService implements Serializable {

    private final ServletContext context;

    public RecipeService(ServletContext context) {
        this.context = context;
    }

    /**
     *
     * @param link
     * @param xslDoc
     * @return
     */
    private InputStream crawlAndTransformXML(String link, String xslDoc) {
        InputStream stream = null;
        try {
            // crawl data
            String rawData = CrawlerUtils.crawlDataFromLink(link);
            // refine data
            stream = XMLTextUtils.refineHTMLToXML(rawData);

            if (stream != null) {
                String xslUrl = context.getRealPath("/") + xslDoc;
                // transforming xml stream apply stylesheet
                stream = TrAXUtils.transformXML(stream, xslUrl);
            }
        } catch (TransformerException | UnsupportedEncodingException ex) {
            Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stream;
    }

    /**
     *
     * @param domain
     * @param origin
     * @param path
     * @param categoryId
     */
    public void crawlRecipesTest(Domain domain, String origin, String path, int categoryId) {
        List<Recipe> listOfRecipes;
        String nextPage;
        String link;
        InputStream stream;
        do {
            link = origin + path;
            stream = crawlAndTransformXML(link, domain.getXslOverview());
            // unmarshalling java class from stream
            Recipes recipes = JAXBUtils.unMarshal(stream, new Recipes());
            listOfRecipes = recipes.getRecipe();
            int size = listOfRecipes.size();
            for (int i = 0; i < size; i++) {
                Recipe tmp = listOfRecipes.get(i);
                link = origin + tmp.getLink();
                stream = crawlAndTransformXML(link, domain.getXslDetail());
                // unmarshalling java class from stream
                Recipe recipe = JAXBUtils.unMarshal(stream, new Recipe());
                recipe.setId(tmp.getId());
                recipe.setLink(link);
                recipe.setImage(tmp.getImage());
                DataHandler.handleRecipeData(recipe);
                System.out.println(recipe.toString());
//                RecipeDAO recDao = new RecipeDAO();
//                CategoryRecipeDAO cateRecDao = new CategoryRecipeDAO();
//                recDao.save(recipe);
//                cateRecDao.save(new CategoryRecipeDTO(tmp.getId().intValue(), categoryId));
            }

            try {
                nextPage = recipes.getNextPage();
            } catch (NullPointerException ex) {
                nextPage = "";
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (nextPage.length() > 0) {
                path = nextPage;
            }
            System.out.println("NextPage=" + nextPage);
        } while (nextPage.length() > 0);
    }

    /**
     *
     * @param domain
     * @param origin
     * @param path
     * @param categoryId
     */
    public void crawlRecipes(Domain domain, String origin, String path, int categoryId) {
        List<Recipe> listOfRecipes;
        String nextPage;
        String link;
        InputStream stream;
        int index = 0;
        do {
            link = origin + path;
            System.out.println("Crawling... " + link);
            stream = crawlAndTransformXML(link, domain.getXslOverview());
            // unmarshalling java class from stream
            Recipes recipes = JAXBUtils.unMarshal(stream, new Recipes());
            listOfRecipes = recipes.getRecipe();
            int size = listOfRecipes.size();
            for (int i = 0; i < 1; i++) {
                Recipe tmp = listOfRecipes.get(i);
                link = origin + tmp.getLink();
                stream = crawlAndTransformXML(link, domain.getXslDetail());
                // unmarshalling java class from stream
                Recipe recipe = JAXBUtils.unMarshal(stream, new Recipe());
                recipe.setId(tmp.getId());
                recipe.setLink(tmp.getLink());
                recipe.setImage(tmp.getImage());
                DataHandler.handleRecipeData(recipe);
                System.out.println(recipe.toString());
                RecipeDAO recDao = new RecipeDAO();
                CategoryRecipeDAO cateRecDao = new CategoryRecipeDAO();
                recDao.save(recipe);
                cateRecDao.save(new CategoryRecipeDTO(tmp.getId().intValue(), categoryId));
            }

            try {
                nextPage = recipes.getNextPage();
            } catch (NullPointerException ex) {
                nextPage = "";
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (nextPage.length() > 0) {
                path = nextPage;
            }
            index++;
//        } while (nextPage.length() > 0);
        } while (index < 2);
    }

    /**
     *
     * @param listOfRecipe
     */
    public void saveRecipes(List<Recipe> listOfRecipe) {
        CategoryDAO repCateDao = new CategoryDAO();
        RecipeDAO repDao = new RecipeDAO();

        listOfRecipe.stream()
                .map((recipe) -> {
                    repDao.save(recipe);
                    return recipe;
                })
                .forEachOrdered((Recipe recipe) -> {
                    recipe.getListCategories().getCategory()
                            .forEach((category) -> {
                                repCateDao.saveCategory(recipe.getId().intValue(), category.intValue());
                            });
                });
    }
}
