package service.action.customeraction;

import bean.Customer;
import dao.DAOFactory;
import dao.interfaces.CustomerDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCustomerService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CustomerDAO dao = DAOFactory.getInstance().getCustomerDAO();
        dao.create(
                new Customer()
                .setAddress(req.getParameter("customer_address"))
                .setName(req.getParameter("customer_name"))
                .setSurname(req.getParameter("customer_surname"))
        );
    }
}
