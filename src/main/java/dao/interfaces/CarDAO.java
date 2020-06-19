package dao.interfaces;

import bean.Car;
import dao.DAO;
import exception.PersistentException;

import java.util.List;

public interface CarDAO extends DAO<Car> {
    Integer createWithIdReturn(Car entity) throws PersistentException;
    List<Car> searchByProducer(String producer) throws PersistentException;
    List<Car> searchByModel(String model) throws PersistentException;
    List<Car> searchByYear(int year) throws PersistentException;
    List<Car> searchByTypeOfFuel(String fuel) throws PersistentException;
    List<Car> searchByColor(String color) throws PersistentException;
    List<Car> searchByTransmission(String transmission) throws PersistentException;
}
