package dao.realization;

import bean.User;
import bean.UserRole;
import dao.connectionpool.ConnectionPool;
import dao.interfaces.UserDAO;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(UserDAOImpl.class);
    @Override
    public User read(String login, String password) throws PersistentException {
        String sql = "SELECT  * FROM user " +
                "WHERE user_login = ? AND user_password = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,login);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
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

    @Override
    public void create(User entity) throws PersistentException {
        String sql = "INSERT INTO `user` (`user_login`, `user_password`, `user_role`) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getRole().toString());
            statement.executeUpdate();
        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при добавлении пользователя в базу данных",e);
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
        String sql = "UPDATE user " +
                " SET user_role = ?, " +
                "user_login = ?, " +
                "user_password = ? " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setString(1, entity.getRole().toString());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();

        } catch(SQLException | ConnectionPoolException e) {
            logger.error(e.getMessage());
            throw new PersistentException("Ошибка при изменении пользователя в базе данных",e);
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
        String sql = "DELETE FROM user WHERE user_id = ?";
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
        String sql = "SELECT  * FROM user ";
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
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
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
        String sql = "SELECT  * FROM user " +
                "WHERE user_id = ?";
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
                user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));

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
