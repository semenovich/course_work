package service.action.customeraction;

import dao.DAOFactory;
import dao.interfaces.CustomerDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadAllCustomerService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CustomerDAO dao = DAOFactory.getInstance().getCustomerDAO();
        req.setAttribute("customers",dao.readAll());
    }
}
