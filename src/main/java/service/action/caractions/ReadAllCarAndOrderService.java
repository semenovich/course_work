package service.action.caractions;

import dao.DAOFactory;
import dao.interfaces.CarDAO;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadAllCarAndOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        req.setAttribute("cars",carDAO.readAll());

        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        req.setAttribute("orders",orderDAO.readAll());

        req.getRequestDispatcher("/WEB-INF/jsp/car/list.jsp").forward(req,resp);
    }
}
