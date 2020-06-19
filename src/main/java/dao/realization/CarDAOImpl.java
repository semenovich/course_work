package dao.realization;

import bean.Car;
import controller.filter.AuthenticationFilter;
import dao.interfaces.CarDAO;
import dao.connectionpool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(CarDAOImpl.class);
    @Override
    public void create(Car entity) throws PersistentException {
        String sql = "INSERT INTO `car` (`car_producer`, `car_model`, `car_year`,`car_color`," +
                "`car_transmission`,`car_fuel`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getProducer());
            statement.setString(2, entity.getModel());
            statement.setInt(3, entity.getYear());
            statement.setString(4, entity.getColor());
            statement.setString(5, entity.getTransmission());
            statement.setString(6, entity.getFuel());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении автомобиля в базу данных",e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(Car entity) throws PersistentException {
        String sql = "UPDATE `car` " +
                " SET `car_producer` = ?, " +
                "`car_model` = ?, " +
                "`car_year` = ?, " +
                "`car_color` = ?, " +
                "`car_transmission` = ?, " +
                "`car_fuel` = ? " +
                "WHERE `car_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setString(1, entity.getProducer());
            statement.setString(2, entity.getModel());
            statement.setInt(3, entity.getYear());
            statement.setString(4, entity.getColor());
            statement.setString(5, entity.getTransmission());
            statement.setString(6, entity.getFuel());
            statement.setInt(7, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении автомобиля в базе данных",e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) throws PersistentException {
        String sql = "DELETE FROM `car` WHERE `car_id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Car> readAll() throws PersistentException {
        String sql = "SELECT * FROM `car` ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<Car> list = new ArrayList<>();
            Car car = null;
            while (resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                list.add(car);
            }
            return list;
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public Car read(int id) throws PersistentException {
        String sql = "SELECT * FROM `car` " +
                "WHERE `car_id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            Car car = null;
            if(resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));

                return car;
            }else {
                return null;
            }
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public Integer createWithIdReturn(Car entity) throws PersistentException {
        String sql = "INSERT INTO `car` (`car_producer`, `car_model`, `car_year`,`car_color`," +
                "`car_transmission`,`car_fuel`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getProducer());
            statement.setString(2, entity.getModel());
            statement.setInt(3, entity.getYear());
            statement.setString(4, entity.getColor());
            statement.setString(5, entity.getTransmission());
            statement.setString(6, entity.getFuel());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            return resultSet.getInt(1);
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Car> searchByProducer(String producer) throws PersistentException {
        return searchString("producer",producer);
    }

    @Override
    public List<Car> searchByModel(String model) throws PersistentException {
        return searchString("model",model);
    }

    @Override
    public List<Car> searchByYear(int year) throws PersistentException {
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM `car` WHERE `car_year` = ? ";
            Connection connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,year);
            resultSet = statement.executeQuery();

            List<Car> list = new ArrayList<>();
            Car car = null;
            while (resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                list.add(car);
            }
            return list;
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Car> searchByTypeOfFuel(String fuel) throws PersistentException {
        return searchString("fuel",fuel);
    }

    @Override
    public List<Car> searchByColor(String color) throws PersistentException {
        return searchString("color",color);
    }

    @Override
    public List<Car> searchByTransmission(String transmission) throws PersistentException {
        return searchString("transmission",transmission);
    }


    private List<Car> searchString(String param,String value) throws PersistentException{

        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM `car` WHERE ";
            Connection connection = ConnectionPool.getInstance().getConnection();
            switch (param){
             case "producer": sql += " `car_producer` = ?";break;
             case "model": sql += " `car_model` = ?";break;
             case "color": sql += " `car_color` = ?";break;
             case "fuel": sql += " `car_fuel` = ?";break;
             case "transmission": sql += " `car_transmission` = ?";break;
            }

            statement = connection.prepareStatement(sql);
            statement.setString(1,value);
            resultSet = statement.executeQuery();

            List<Car> list = new ArrayList<>();
            Car car = null;
            while (resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));

                list.add(car);
            }
            return list;
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {
                logger.error(e.getMessage());
            }
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
    }


}
