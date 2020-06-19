package dao.realization;

import bean.Car;
import bean.Customer;
import bean.Order;
import bean.User;
import dao.connectionpool.ConnectionPool;
import dao.interfaces.AnalysisDAO;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisDAOImpl implements AnalysisDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(AnalysisDAOImpl.class);

    public Order expensiveCar() throws PersistentException {

        String sql = "SELECT car_id,order_price,car_producer,car_model,car_year,car_color,car_fuel,car_transmission " +
                " from `order` inner join car on `order`.order_car_id = car.car_id " +
                " where order_price = (" +
                "    select max(order_price) " +
                "    from `order`" +
                "    )";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Car car = null;
            Order order = null;
            if(resultSet.next()) {
                order = new Order();
                car = new Car();
                car.setId(resultSet.getInt("car_id"));
                car.setProducer(resultSet.getString("car_producer"));
                car.setModel(resultSet.getString("car_model"));
                car.setYear(resultSet.getInt("car_year"));
                car.setColor(resultSet.getString("car_color"));
                car.setTransmission(resultSet.getString("car_transmission"));
                car.setFuel(resultSet.getString("car_fuel"));
                order.setCar(car);
                order.setPrice(resultSet.getInt("order_price"));
            }
            return order;
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
    public Car popularColor() throws PersistentException {
        String sql = "select car_color,count(car_color) as col " +
                "from car  inner join `order` o on car.car_id = o.order_car_id" +
                " where car_id = order_car_id " +
                "group by car_color" +
                " order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return
                        (Car) new Car()
                                .setColor(resultSet.getString("car_color"))
                                .setId(resultSet.getInt("col"));
            }
            return null;
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
    public Car popularProducer() throws PersistentException {
        String sql = "select car_producer,count(car_producer) as col " +
                "from car  inner join `order` o on car.car_id = o.order_car_id" +
                " where car_id = order_car_id " +
                "group by car_producer" +
                " order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return
                        (Car) new Car()
                                .setProducer(resultSet.getString("car_producer"))
                                .setId(resultSet.getInt("col"));
            }
            return null;
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
    public Car popularFuel() throws PersistentException {
        String sql = "select car_fuel,count(car_fuel) as col " +
                "from car  inner join `order` o on car.car_id = o.order_car_id" +
                " where car_id = order_car_id " +
                "group by car_fuel" +
                " order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return
                        (Car) new Car()
                                .setFuel(resultSet.getString("car_fuel"))
                                .setId(resultSet.getInt("col"));
            }
            return null;
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
    public Car popularTransmission() throws PersistentException {
        String sql = "select car_transmission,count(car_transmission) as col " +
                "from car  inner join `order` o on car.car_id = o.order_car_id" +
                " where car_id = order_car_id " +
                "group by car_transmission" +
                " order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return
                        (Car) new Car()
                                .setTransmission(resultSet.getString("car_transmission"))
                                .setId(resultSet.getInt("col"));
            }
            return null;
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
    public Double avgPrice() throws PersistentException {
        String sql = "select avg(order_price) as `price` from `order`";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getDouble("price");
            }
            return null;
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
    public Double avgCarYear() throws PersistentException {
        String sql = "select avg(car_year) as `year` from car";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getDouble("year");
            }
            return null;
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
    public User activeSeller() throws PersistentException {
        String sql = "select order_seller_id,count(order_seller_id) as col " +
                "from `order` " +
                "group by order_seller_id " +
                "order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return (User) new UserAndUserInfoDAOImpl()
                        .read(resultSet.getInt("order_seller_id"))
                        .setId(resultSet.getInt("col"));
            }
            return null;
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
    public Customer activeCustomer() throws PersistentException {
        String sql = "select order_customer_id,count(order_customer_id) as col " +
                "from `order` " +
                "group by order_customer_id " +
                "order by col desc";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("order_customer_id");
                Customer customer = new CustomerDAOImpl().readById(id);
                customer.setId(resultSet.getInt("col"));
                return customer;
            }
            return null;
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
