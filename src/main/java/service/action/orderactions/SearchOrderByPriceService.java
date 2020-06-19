package service.action.orderactions;

import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchOrderByPriceService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        OrderDAO dao = DAOFactory.getInstance().getOrderDAO();
        req.setAttribute("orders",dao.searchByPrice(Double.parseDouble(req.getParameter("value"))));
        req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req,resp);
    }
}
