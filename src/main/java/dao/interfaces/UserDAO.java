package dao.interfaces;

import bean.User;
import dao.DAO;
import exception.PersistentException;

public interface UserDAO extends DAO<User> {
    User read(String login, String password) throws PersistentException;
}
