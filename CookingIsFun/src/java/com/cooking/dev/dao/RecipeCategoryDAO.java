/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import com.cooking.dev.jaxb.Path;
import java.util.List;

/**
 *
 * @author huynh
 */
public interface RecipeCategoryDAO {

    public boolean save(Path path);

    public boolean save(List<Path> paths);

    public boolean update(Path path);

    public Path findById(int id);

    public List<Path> findAll();
}
