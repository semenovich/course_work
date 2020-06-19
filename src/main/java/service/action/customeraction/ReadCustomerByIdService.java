package service.action.customeraction;

import dao.DAOFactory;
import dao.interfaces.CustomerDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadCustomerByIdService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CustomerDAO dao = DAOFactory.getInstance().getCustomerDAO();
        req.setAttribute("customer",dao.read(Integer.parseInt(req.getParameter("customer_id"))));
    }
}
