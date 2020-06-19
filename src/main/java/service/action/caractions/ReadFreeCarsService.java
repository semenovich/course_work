package service.action.caractions;

import bean.Car;
import bean.Order;
import dao.DAOFactory;
import dao.interfaces.CarDAO;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFreeCarsService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        List<Car> carList = carDAO.readAll();
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        List<Order> orderList = orderDAO.readAll();

        List<Car> freeCars = new ArrayList<>();
        boolean temp;
        for (Car car:carList) {
            temp = true;
            for (Order order:orderList) {
                if(car.getId()==order.getCar().getId()){
                    temp = false;
                }
            }
            if(temp==true){
                freeCars.add(car);
            }
        }
        req.setAttribute("freeCars",freeCars);
        req.getRequestDispatcher("/WEB-INF/jsp/car/freeCars.jsp").forward(req, resp);
    }
}
