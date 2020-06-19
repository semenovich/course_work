package service.action.caractions;

import dao.DAOFactory;
import dao.interfaces.CarDAO;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;
import service.action.orderactions.ReadAllOrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchCarByColorService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        req.setAttribute("cars",carDAO.searchByColor(req.getParameter("value")));
        new ReadAllOrderService().execute(req,resp);
        req.getRequestDispatcher("/WEB-INF/jsp/car/list.jsp").forward(req,resp);
    }
}
