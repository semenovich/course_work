package service.action.orderadcaractions;

import dao.DAOFactory;
import dao.interfaces.OrderAndCarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class SearchOrderAndCarByDateService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        OrderAndCarDAO dao = DAOFactory.getInstance().getOrderAndCarDAO();
        req.setAttribute("orders",dao.searchByDate(Date.valueOf(req.getParameter("order_date"))));
    }
}
