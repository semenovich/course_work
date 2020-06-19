package dao.interfaces;

import bean.Customer;
import dao.DAO;
import exception.PersistentException;

public interface CustomerDAO extends DAO<Customer> {
    Customer readById(int id) throws PersistentException;
}
