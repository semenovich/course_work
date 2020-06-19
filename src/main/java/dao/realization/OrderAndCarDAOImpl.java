package dao.realization;

import bean.Car;
import bean.Order;
import bean.User;
import dao.interfaces.OrderAndCarDAO;
import dao.connectionpool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderAndCarDAOImpl implements OrderAndCarDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(OrderAndCarDAOImpl.class);
    @Override
    public void create(Order entity) throws PersistentException {
        String sql = "INSERT INTO `customer` (`customer_name`, `customer_surname`, `customer_address`)" +
                " VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getCustomer().getName());
            statement.setString(2, entity.getCustomer().getSurname());
            statement.setString(3, entity.getCustomer().getAddress());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                String sql2 = "INSERT INTO `order` (`order_price`, `order_date`, `order_car_id`," +
                        "`order_seller_id`,`order_customer_id`) " +
                        "VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql2);
                statement.setDouble(1,entity.getPrice());
                statement.setDate(2,entity.getDate());
                statement.setInt(3, entity.getCar().getId());
                statement.setInt(4,entity.getUser().getId());
                statement.setInt(5, resultSet.getInt(1));

                statement.executeUpdate();
            } else {
                throw new PersistentException("Ошибка при добавлении заказа или покупаеля в базу данных");
            }
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении заказа или покупаеля в базу данных",e);
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
    public void update(Order entity) throws PersistentException {
        String sql = "UPDATE `order` INNER JOIN `customer` ON `order_customer_id` = `customer_id` " +
                " SET `order_price` = ?, " +
                "`order_date` = ?, " +
                "`order_seller_id` = ?, " +
                "`order_car_id` = ?, " +
                "`customer_name` = ?, " +
                "`customer_surname` = ?, " +
                "`customer_address` = ? " +
                "WHERE `order_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setDouble(1, entity.getPrice());
            statement.setDate(2, entity.getDate());
            statement.setInt(3, entity.getUser().getId());
            statement.setInt(4, entity.getCar().getId());
            statement.setString(5, entity.getCustomer().getName());
            statement.setString(6, entity.getCustomer().getSurname());
            statement.setString(7, entity.getCustomer().getAddress());
            statement.setInt(8, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении заказа или покупаеля в базе данных",e);
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
        String sql = "DELETE FROM `order` WHERE `order_id` = ?";
        String sql2 = "DELETE FROM `customer` WHERE `customer_id` = ?";

        int customerId = read(id).getCustomer().getId();

        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(sql);
            statement2 = connection.prepareStatement(sql2);

            statement.setInt(1,id);
            statement2.setInt(1, customerId);

            statement.executeUpdate();
            statement2.executeUpdate();

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
    public List<Order> readAll() throws PersistentException {
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id` ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            Order order = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){

                order = new Order();
                user = new User();
                car = new Car();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                list.add(order);
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
    public Order read(int id) throws PersistentException {
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id` " +
                "WHERE `order_id` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            Order order = null;
            User user = null;
            Car car = null;
            if(resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));

                order.setCar(car);
                return order;
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
    public List<Order> searchByDate(Date date) throws PersistentException {
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id `" +
                "WHERE `order_date` = ? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setDate(1,date);
            resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            Order order = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                list.add(order);
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
    public List<Order> searchByPrice(double price) throws PersistentException{
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id `" +
                "WHERE `order_price` = ? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,price);
            resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            Order order = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                list.add(order);
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
    public List<Order> searchByUserId(int id) throws PersistentException{
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id `" +
                "WHERE `order_seller_id` = ? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            Order order = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                list.add(order);
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
    public List<Order> searchByCarId(int id) throws PersistentException{
        String sql = "SELECT * FROM `order` INNER JOIN `car` ON `order_car_id` = `car_id `" +
                "WHERE `order_car_id` = ? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();
            Order order = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);
                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                list.add(order);
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
