package dao;

import bean.Entity;
import exception.PersistentException;

import java.util.List;

public interface DAO<T extends Entity> {
    void create(T entity) throws PersistentException;
    void update(T entity) throws PersistentException;
    void delete(int id) throws PersistentException;
    List<T> readAll() throws PersistentException;
    T read(int id) throws PersistentException;
}
