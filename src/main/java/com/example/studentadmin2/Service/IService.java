package com.example.studentadmin2.Service;

import java.util.List;

public interface IService<T> {

    List<T> findAll();

    T findById(int id);

    T create(T t);

    T update(T t);

    boolean delete(int id);

}