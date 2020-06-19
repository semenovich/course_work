package service.action.orderactions;

import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadOrderByIdService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        req.setAttribute("order",orderDAO.read(Integer.parseInt(req.getParameter("order_id"))));
    }
}
