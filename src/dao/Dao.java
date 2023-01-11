package dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    T add(T t);
    boolean delete(T t);
    T update(T t);
    T findOne(int id);
}
