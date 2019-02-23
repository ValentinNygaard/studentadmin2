package com.example.studentadmin2.Repository;

import java.util.List;

public interface IRepo<T> {

    List<T> findAll();

    T findById(int id);

    T create(T t);

    boolean delete(int id);

    T update(T t);

}
