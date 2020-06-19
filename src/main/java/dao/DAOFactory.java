package dao;

import dao.interfaces.*;
import dao.realization.*;


public class DAOFactory {
    private final static DAOFactory dao = new DAOFactory();

    private UserAndUserInfoDAO userAndUserInfoDAO = new UserAndUserInfoDAOImpl();
    private OrderAndCarDAO orderAndCarDAO = new OrderAndCarDAOImpl();
    private CarDAO carDAO = new CarDAOImpl();
    private UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private AnalysisDAO analysisDAO = new AnalysisDAOImpl();

    public static DAOFactory getInstance(){
        return dao;
    }

    public UserAndUserInfoDAO getUserAndUserInfoDAO(){
        return userAndUserInfoDAO;
    }
    public OrderAndCarDAO getOrderAndCarDAO(){
        return orderAndCarDAO;
    }
    public CarDAO getCarDAO(){
        return carDAO;
    }
    public UserInfoDAO getUserInfoDAO(){
        return userInfoDAO;
    }
    public UserDAO getUserDAO(){
        return userDAO;
    }
    public OrderDAO getOrderDAO(){
        return orderDAO;
    }
    public CustomerDAO getCustomerDAO(){
        return customerDAO;
    }
    public AnalysisDAO getAnalysisDAO(){
        return analysisDAO;
    }

    private DAOFactory(){}
}
