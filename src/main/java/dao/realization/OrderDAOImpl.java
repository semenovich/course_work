package dao.realization;

import bean.*;
import dao.connectionpool.ConnectionPool;
import dao.interfaces.OrderDAO;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(OrderDAOImpl.class);
    @Override
    public void create(Order entity) throws PersistentException {
        String sql = "INSERT INTO `order` (`order_price`, `order_date`, `order_car_id`," +
                "`order_seller_id`,`order_customer_id`) " +
                "VALUES (?, ?, ?, ?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, entity.getPrice());
            statement.setDate(2, entity.getDate());
            statement.setInt(3, entity.getCar().getId());
            statement.setInt(5, entity.getCustomer().getId());
            statement.setInt(4, entity.getUser().getId());
            statement.executeUpdate();
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении заказа в базу данных",e);
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
        String sql = "UPDATE `order` " +
                " SET `order_price` = ?, " +
                "`order_date` = ?, " +
                "`order_seller_id` = ?," +
                "`order_car_id` = ?," +
                "`order_customer_id` = ? " +
                " WHERE order_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);

            statement.setDouble(1, entity.getPrice());
            statement.setDate(2, entity.getDate());
            statement.setInt(3, entity.getUser().getId());
            statement.setInt(4, entity.getCar().getId());
            statement.setInt(5, entity.getCustomer().getId());
            statement.setInt(6, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении заказа в базе данных",e);
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
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            throw new PersistentException(e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<Order> readAll() throws PersistentException {
        String sql = "SELECT * FROM `order` ";
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
            Customer customer = null;
            while (resultSet.next()){

                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                car.setId(resultSet.getInt("order_car_id"));
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
        String sql = "SELECT * FROM `order` INNER JOIN `customer` ON `order_customer_id`=`customer_id` " +
                "INNER JOIN `car` ON `order`.`order_car_id` = `car_id` INNER JOIN `user` " +
                "ON `order_seller_id` = `user_id` INNER JOIN `user_info` ON " +
                "`user_id` = `user_info_id`"+
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
            Customer customer = null;
            if(resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setSurname(resultSet.getString("customer_surname"));
                customer.setAddress(resultSet.getString("customer_address"));
                order.setCustomer(customer);

                user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_info_name"));
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                order.setUser(user);

                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

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
    public List<Order> searchById(int id) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
                "WHERE `order_id` = ? ";
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
    public List<Order> searchByCarId(int id) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
    public List<Order> searchByCustomerId(int id) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
                "WHERE `order_customer_id` = ? ";
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
    public List<Order> searchBySellerId(int id) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
    public List<Order> searchByDate(Date date) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
    public List<Order> searchByPrice(double price) throws PersistentException {
        String sql = "SELECT * FROM `order` " +
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
            Customer customer = null;
            User user = null;
            Car car = null;
            while (resultSet.next()){
                order = new Order();
                user = new User();
                car = new Car();
                customer = new Customer();

                user.setId(resultSet.getInt("order_seller_id"));
                order.setUser(user);

                order.setPrice(resultSet.getDouble("order_price"));
                order.setId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));

                customer.setId(resultSet.getInt("order_customer_id"));
                order.setCustomer(customer);

                car.setId(resultSet.getInt("order_car_id"));
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
