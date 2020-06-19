package dao.interfaces;

import bean.Order;
import bean.User;
import dao.DAO;
import exception.PersistentException;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> searchById(int id) throws PersistentException;
    List<Order> searchByCarId(int id) throws  PersistentException;
    List<Order> searchByCustomerId(int id) throws  PersistentException;
    List<Order> searchBySellerId(int id) throws  PersistentException;
    List<Order> searchByDate(Date date) throws  PersistentException;
    List<Order> searchByPrice(double price) throws  PersistentException;
}
