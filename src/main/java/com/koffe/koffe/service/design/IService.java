package com.koffe.koffe.service.design;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(int id);

    boolean save(T t);

    boolean update(T t);

    void deleteById(int id);
}
