package dao.realization;

import bean.Car;
import bean.Customer;
import dao.connectionpool.ConnectionPool;
import dao.interfaces.CustomerDAO;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(CustomerDAOImpl.class);
    @Override
    public void create(Customer entity) throws PersistentException {
        String sql = "INSERT INTO `customer` (`customer_name`, `customer_surname`, `customer_address`)" +
                " VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getAddress());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении покупаеля в базу данных",e);
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
    public void update(Customer entity) throws PersistentException {
        String sql = "UPDATE `customer` " +
                " SET `customer_name` = ?, " +
                "`customer_surname` = ?, " +
                "`customer_address` = ? " +
                "WHERE `customer_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getAddress());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении покупаеля в базе данных",e);
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
        String sql = "DELETE FROM `customer` WHERE `customer_id` = ?";
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
    public List<Customer> readAll() throws PersistentException {
        String sql = "SELECT * FROM `customer` ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<Customer> list = new ArrayList<>();
            Customer customer = null;
            while (resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setSurname(resultSet.getString("customer_surname"));
                customer.setAddress(resultSet.getString("customer_address"));
                list.add(customer);
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
    public Customer read(int id) throws PersistentException {
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
            Customer customer = null;
            if(resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setSurname(resultSet.getString("customer_surname"));
                customer.setAddress(resultSet.getString("customer_address"));
                return customer;
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
    public Customer readById(int id) throws PersistentException {
        String sql = "SELECT * FROM `customer` " +
                "WHERE `customer_id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            Customer customer = null;
            if(resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setSurname(resultSet.getString("customer_surname"));
                customer.setAddress(resultSet.getString("customer_address"));
                return customer;
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
}
