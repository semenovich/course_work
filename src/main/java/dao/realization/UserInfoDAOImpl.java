package dao.realization;
import bean.User;
import dao.connectionpool.ConnectionPool;
import dao.interfaces.UserInfoDAO;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAOImpl implements UserInfoDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(UserInfoDAOImpl.class);
    @Override
    public void create(User entity) throws PersistentException {
        String sql = "INSERT INTO `user_info` (`user_info_id`, `user_info_name`, `user_info_surname`," +
                "`user_info_email`,`user_info_telephone`) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,entity.getId());
            statement.setString(2,entity.getName());
            statement.setString(3,entity.getSurname());
            statement.setString(4,entity.getEmail());
            statement.setString(5,entity.getTelephone());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении информации о пользователе в базу данных",e);
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
    public void update(User entity) throws PersistentException {
        String sql = "UPDATE user_info " +
                " SET user_info_name = ?, " +
                "user_info_surname = ?, " +
                "user_info_email = ?, " +
                "user_info_telephone = ? " +
                "WHERE user_info_id = ?";
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getTelephone());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении информации о пользователе в базе данных",e);
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
        String sql = "DELETE FROM user_info WHERE user_info_id = ?";
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
    public List<User> readAll() throws PersistentException {
        String sql = "SELECT  * FROM user_info ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            User user = null;
            List<User> list = new ArrayList<>();

            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("user_info_id"));
                user.setName(resultSet.getString("user_info_name"));
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                list.add(user);
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
    public User read(int id) throws PersistentException {
        String sql = "SELECT  * FROM user_info " +
                "WHERE user_info_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("user_info_id"));
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setName(resultSet.getString("user_info_name"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                return user;
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
