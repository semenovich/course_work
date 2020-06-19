package dao.interfaces;

import bean.Car;
import bean.Customer;
import bean.Order;
import bean.User;
import dao.DAO;
import exception.PersistentException;

public interface AnalysisDAO{
    Order expensiveCar() throws PersistentException;

    Car popularColor() throws PersistentException;
    Car popularProducer() throws PersistentException;
    Car popularFuel() throws PersistentException;
    Car popularTransmission() throws PersistentException;

    Double avgPrice()throws PersistentException;
    Double avgCarYear() throws PersistentException;

    User activeSeller() throws PersistentException;
    Customer activeCustomer() throws PersistentException;
}
