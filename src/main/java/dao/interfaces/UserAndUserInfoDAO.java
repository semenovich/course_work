package dao.interfaces;

import bean.User;
import dao.DAO;
import exception.PersistentException;

import java.util.List;

public interface UserAndUserInfoDAO extends DAO<User> {
    User read(String login, String password) throws PersistentException;
    List<User> searchByRole(String role) throws  PersistentException;
    List<User> searchByEmail(String email) throws  PersistentException;
    List<User> searchByTelephone(String telephone) throws  PersistentException;
    List<User> searchByName(String name) throws  PersistentException;
    List<User> searchBySurname(String surname) throws  PersistentException;
    void block(int id)throws  PersistentException;
    void unblock(int id)throws  PersistentException;
}
