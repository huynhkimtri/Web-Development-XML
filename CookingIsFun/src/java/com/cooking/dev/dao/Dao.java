/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cooking.dev.dao;

import java.util.List;

/**
 *
 * @author huynh
 * @param <T>
 */
public interface Dao<T> {

    List<T> getList();

    T getById(int id);

    boolean save(T t);

    boolean saveList(List<T> ts);

    void update(T t);

    void delete(T t);

    int total();
}
