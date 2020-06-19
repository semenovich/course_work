package dao.interfaces;

import bean.Order;
import dao.DAO;
import exception.PersistentException;

import java.sql.Date;
import java.util.List;

public interface OrderAndCarDAO extends DAO<Order> {
    List<Order> searchByDate(Date date) throws PersistentException;
    List<Order> searchByPrice(double price) throws PersistentException;
    List<Order> searchByUserId(int id) throws PersistentException;
    List<Order> searchByCarId(int id) throws PersistentException;
}
