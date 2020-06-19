package service.action.caractions;

import bean.Car;
import bean.Order;
import bean.User;
import bean.comparator.*;
import dao.DAOFactory;
import dao.interfaces.CarDAO;
import dao.interfaces.OrderDAO;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareCarService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Comparator<Car> comparator = null;
        switch (req.getParameter("comparator")){
            case "producer":comparator = new CompareCarByProducer(); break;
            case "model":comparator = new CompareCarByModel();break;
            case "year":comparator = new CompareCarByYear();break;
            case "transmission":comparator = new CompareCarByTransmission();break;
            case "fuel":comparator = new CompareCarByFuel();break;
            case "color":comparator = new CompareCarByColor();break;
        }
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        List<Car> listCar = carDAO.readAll();
        List<Order> listOrder = orderDAO.readAll();
        Collections.sort(listCar,comparator);
        req.setAttribute("cars",listCar);
        req.setAttribute("orders",listOrder);
        req.getRequestDispatcher("/WEB-INF/jsp/car/list.jsp").forward(req,resp);
    }
}
