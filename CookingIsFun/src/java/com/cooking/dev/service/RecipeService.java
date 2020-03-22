/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.service;

import com.cooking.dev.dao.Dao;
import com.cooking.dev.dao.impl.CategoryDao;
import com.cooking.dev.dao.impl.RecipeDao;
import com.cooking.dev.jaxb.Domain;
import com.cooking.dev.jaxb.ListCategories;
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
     * @param domain
     * @param origin
     * @param path
     * @return
     */
    public List<Recipe> crawlRecipes(Domain domain, String origin, String path) {
        List<Recipe> listOfRecipes = null;
        String nextPage = "";
        String link;
        String rawData;
        InputStream stream;
        do {
            link = origin + path;
            System.out.println("Link=" + link);
            try {
                // crawl data
                rawData = CrawlerUtils.crawlDataFromLink(link);
                // refine data
                stream = XMLTextUtils.refineHTMLToXML(rawData);
                if (stream != null) {
                    String xslOverview = context.getRealPath("/") + domain.getXslOverview();
                    // transforming xml stream apply stylesheet
                    stream = TrAXUtils.transformXML(stream, xslOverview);
                    // parse stream to xml file
//                    DOMUtils.parseXML(stream, context);
                    // unmarshalling java class from stream
                    Recipes recipes = JAXBUtils.unmarshalJavaObject(stream, new Recipes());
                    listOfRecipes = recipes.getRecipe();
                    for (int i = 0; i < 5; i++) {
                        Recipe tmp = listOfRecipes.get(i);
                        link = origin + tmp.getLink();
                        System.out.println("Link:" + link);
                        // crawl data
                        rawData = CrawlerUtils.crawlDataFromLink(link);
                        // refine data
                        stream = XMLTextUtils.refineHTMLToXML(rawData);
                        if (stream != null) {
                            String xslDetail = context.getRealPath("/") + domain.getXslDetail();
                            // transforming xml stream apply stylesheet
                            stream = TrAXUtils.transformXML(stream, xslDetail);
                            // parse stream to xml file
//                            DOMUtils.parseXML(stream, context);
                            // unmarshalling java class from stream
                            Recipe recipe = JAXBUtils.unmarshalJavaObject(stream, new Recipe());
                            recipe.setLink(tmp.getLink());
                            recipe.setId(tmp.getId());
                            listOfRecipes.add(recipe);
                        }
                    }
                    /*
                    for (Recipe item : listOfRecipes) {
                        link = origin + item.getLink();
                        System.out.println("Link:" + link);
                        // crawl data
                        rawData = CrawlerUtils.crawlDataFromLink(link);
                        // refine data
                        stream = XMLTextUtils.refineHTMLToXML(rawData);
                        if (stream != null) {
                            String xslDetail = context.getRealPath("/") + domain.getXslDetail();
                            // transforming xml stream apply stylesheet
                            stream = TrAXUtils.transformXML(stream, xslDetail);
                            // parse stream to xml file
                            DOMUtils.parseXML(stream, context);
                            // unmarshalling java class from stream
                            Recipe recipe = JAXBUtils.unmarshalJavaObject(stream, new Recipe());
                            ListCategories categories = new ListCategories();
                            recipe.setListCategories(categories);
                            listOfRecipes.add(recipe);
                            System.out.println("Recipe: " + recipe.toString());
                        }
                    }*/
//                    nextPage = recipes.getNextPage();
                }
            } catch (TransformerException | UnsupportedEncodingException ex) {
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (nextPage.length() > 0) {
                path = nextPage;
            }
            System.out.println("NextPage=" + nextPage);
            System.out.println("Link=" + path);
        } while (nextPage.length() > 0);
        return listOfRecipes;
    }

    /**
     *
     * @param domain
     * @param origin
     * @param path
     * @return
     */
    public List<Recipe> crawlRecipesTest(Domain domain, String origin, String path) {
        List<Recipe> listOfRecipes = null;
        String nextPage = "";
        String link;
        String rawData;
        InputStream stream;
        int s = 5;
        do {
            link = origin + path;
            System.out.println("Link=" + link);
            try {
                // crawl data
                rawData = CrawlerUtils.crawlDataFromLink(link);
                // refine data
                stream = XMLTextUtils.refineHTMLToXML(rawData);
                if (stream != null) {
                    String xslOverview = context.getRealPath("/") + domain.getXslOverview();
                    // transforming xml stream apply stylesheet
                    stream = TrAXUtils.transformXML(stream, xslOverview);
                    // unmarshalling java class from stream
                    Recipes recipes = JAXBUtils.unmarshalJavaObject(stream, new Recipes());
                    listOfRecipes = recipes.getRecipe();
                    for (int i = 0; i < 5; i++) {
                        Recipe tmp = listOfRecipes.get(i);
                        link = origin + tmp.getLink();
                        System.out.println("Link:" + link);
                        // crawl data
                        rawData = CrawlerUtils.crawlDataFromLink(link);
                        // refine data
                        stream = XMLTextUtils.refineHTMLToXML(rawData);
                        if (stream != null) {
                            String xslDetail = context.getRealPath("/") + domain.getXslDetail();
                            // transforming xml stream apply stylesheet
                            stream = TrAXUtils.transformXML(stream, xslDetail);
                            // unmarshalling java class from stream
                            Recipe recipe = JAXBUtils.unmarshalJavaObject(stream, new Recipe());
                            recipe.setLink(tmp.getLink());
                            recipe.setId(tmp.getId());
                            listOfRecipes.add(recipe);
                            System.out.println(recipe.toString());
                        }
                    }
                    nextPage = recipes.getNextPage();
                }
            } catch (TransformerException | UnsupportedEncodingException ex) {
                Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (nextPage.length() > 0) {
                path = nextPage;
            }
            System.out.println("NextPage=" + nextPage);
            System.out.println("Link=" + path);
            s -= 1;
        } while (s > 0);
        return listOfRecipes;
    }

    /**
     *
     * @param listOfRecipe
     */
    public void saveRecipes(List<Recipe> listOfRecipe) {
        CategoryDao repCateDao = new CategoryDao();
        RecipeDao repDao = new RecipeDao();

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
