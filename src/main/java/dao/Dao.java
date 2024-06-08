package dao;

import java.util.List;

public interface Dao<T> {

    T findById(int id);

    void save(T t);

    void update(T t);

    void delete(T t);

    void multipleDelete();

    List<T> findByFirstLetter(String firstLetter);

    List<T> sortByName();

    List<T> findAll();
}
