package service.action.orderactions;

import bean.Car;
import bean.Customer;
import bean.Order;
import bean.User;
import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class UpdateOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        orderDAO.update(
                (Order) new Order()
                        .setDate(new Date(Integer.parseInt(req.getParameter("date_year"))-1900,
                                Integer.parseInt(req.getParameter("date_month"))-1,
                                Integer.parseInt(req.getParameter("date_day"))+1))
                .setPrice(Double.valueOf(req.getParameter("order_price")))
                .setUser((User) new User().setId(Integer.parseInt(req.getParameter("order_seller_id"))))
                .setCar((Car) new Car().setId(Integer.parseInt(req.getParameter("order_car_id"))))
                .setCustomer((Customer) new Customer().setId(Integer.parseInt(req.getParameter("order_customer_id"))))
                .setId(Integer.parseInt(req.getParameter("order_id")))
        );
        new ReadAllOrderService().execute(req,resp);
    }
}
