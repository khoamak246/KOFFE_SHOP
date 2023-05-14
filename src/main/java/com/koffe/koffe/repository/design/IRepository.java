package com.koffe.koffe.repository.design;

import java.util.List;

public interface IRepository<T> {

    List<T> findAll();

    T findById(int id);

    boolean save(T t);

    boolean update(T t);

    void deleteById(int id);
}
