package service.action.orderactions;

import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        orderDAO.delete(Integer.parseInt(req.getParameter("order_id")));
        new ReadAllOrderService().execute(req,resp);
    }
}
