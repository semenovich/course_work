package service.action.customeraction;

import bean.Customer;
import dao.DAOFactory;
import dao.interfaces.CustomerDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCustomerService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CustomerDAO dao = DAOFactory.getInstance().getCustomerDAO();
        dao.delete(Integer.parseInt(req.getParameter("customer_id")));
    }
}
