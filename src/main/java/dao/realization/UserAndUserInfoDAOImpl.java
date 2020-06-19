package dao.realization;

import bean.User;
import bean.UserRole;
import dao.interfaces.UserAndUserInfoDAO;
import dao.connectionpool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAndUserInfoDAOImpl implements UserAndUserInfoDAO {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(UserAndUserInfoDAOImpl.class);
    @Override
    public void create(User entity) throws PersistentException {

        String sql = "INSERT INTO `user` (`user_login`, `user_password`, `user_role`) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getRole().toString());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                String sql2 = "INSERT INTO `user_info` (`user_info_id`, `user_info_name`, `user_info_surname`," +
                        "`user_info_email`,`user_info_telephone`) " +
                        "VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql2);
                statement.setInt(1,resultSet.getInt(1));
                statement.setString(2,entity.getName());
                statement.setString(3,entity.getSurname());
                statement.setString(4,entity.getEmail());
                statement.setString(5,entity.getTelephone());
                statement.executeUpdate();
            } else {
                throw new PersistentException("Ошибка при добавлении пользователя в базу данных");
            }
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
    public void update(User entity) throws PersistentException{
        String sql = "UPDATE user INNER JOIN user_info ON user.user_id = user_info.user_info_id " +
                " SET user_role = ?, " +
                "user_login = ?, " +
                "user_password = ?, " +
                "user_info_name = ?, " +
                "user_info_surname = ?, " +
                "user_info_email = ?, " +
                "user_info_telephone = ? " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
            statement.setString(1, entity.getRole().toString());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getName());
            statement.setString(5, entity.getSurname());
            statement.setString(6, entity.getEmail());
            statement.setString(7, entity.getTelephone());
            statement.setInt(8, entity.getId());
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
    public void delete(int id) throws PersistentException{
        String sql2 = "DELETE FROM user_info WHERE user_info_id = ?";
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(sql);
            statement2 = connection.prepareStatement(sql2);

            statement.setInt(1,id);
            statement2.setInt(1, id);

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
    public List<User> readAll() throws PersistentException{
        String sql = "SELECT  * FROM user INNER JOIN user_info ON user.user_id = user_info.user_info_id";
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
                user.setName(resultSet.getString("user_info_name"));
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                user.setStatus(resultSet.getBoolean("user_status"));
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
    public User read(int id) throws PersistentException{
        String sql = "SELECT  * FROM user INNER JOIN user_info ON user.user_id = user_info.user_info_id " +
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
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setPassword(resultSet.getString("user_password"));
                user.setName(resultSet.getString("user_info_name"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                user.setStatus(resultSet.getBoolean("user_status"));
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
    public User read(String login, String password) throws PersistentException {
        String sql = "SELECT  * FROM user INNER JOIN user_info ON user.user_id = user_info.user_info_id " +
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
                user.setSurname(resultSet.getString("user_info_surname"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.setEmail(resultSet.getString("user_info_email"));
                user.setTelephone(resultSet.getString("user_info_telephone"));
                user.setName(resultSet.getString("user_info_name"));
                user.setStatus(resultSet.getBoolean("user_status"));
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
    public List<User> searchByRole(String role) throws PersistentException {
        return searchString("role",role);
    }

    @Override
    public List<User> searchByEmail(String email) throws PersistentException {
        return searchString("email",email);
    }

    @Override
    public List<User> searchByTelephone(String telephone) throws PersistentException {
        return searchString("telephone",telephone);
    }

    @Override
    public List<User> searchByName(String name) throws PersistentException {
        return searchString("name",name);
    }

    @Override
    public List<User> searchBySurname(String surname) throws PersistentException {
        return searchString("surname",surname);
    }

    @Override
    public void block(int id) throws PersistentException {
        String sql = "UPDATE user " +
                " SET user_status = false " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
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
    public void unblock(int id) throws PersistentException {
        String sql = "UPDATE user " +
                " SET user_status = true " +
                "WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionPool.getInstance().getConnection()
                    .prepareStatement(sql);
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

    private List<User> searchString(String param,String value) throws PersistentException{
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            String sql = "SELECT  * FROM user INNER JOIN user_info ON user.user_id = user_info.user_info_id " +
                    "WHERE ";
            Connection connection = ConnectionPool.getInstance().getConnection();
            switch (param){
                case "surname": sql += " `user_info_surname` = ?";break;
                case "name": sql += " `user_info_name` = ?";break;
                case "telephone": sql += " `user_info_telephone` = ?";break;
                case "email": sql += " `user_info_email` = ?";break;
                case "role": sql += " `user_role` = ?";break;
            }

            statement = connection.prepareStatement(sql);
            statement.setString(1,value);
            resultSet = statement.executeQuery();

            List<User> list = new ArrayList<>();

            User user = null;
            while (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("user_login"));
            user.setPassword(resultSet.getString("user_password"));
            user.setRole(UserRole.valueOf(resultSet.getString("user_role")));
            user.setName(resultSet.getString("user_info_name"));
            user.setSurname(resultSet.getString("user_info_surname"));
            user.setEmail(resultSet.getString("user_info_email"));
            user.setTelephone(resultSet.getString("user_info_telephone"));
            user.setStatus(resultSet.getBoolean("user_status"));
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
}
