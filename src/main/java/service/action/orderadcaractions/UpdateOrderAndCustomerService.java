package service.action.orderadcaractions;

import bean.Car;
import bean.Order;
import bean.User;
import dao.DAOFactory;
import dao.interfaces.OrderAndCarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class UpdateOrderAndCustomerService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        OrderAndCarDAO dao = DAOFactory.getInstance().getOrderAndCarDAO();
        dao.update(
                (Order) new Order()
                        .setDate(Date.valueOf(req.getParameter("date")))
                        .setPrice(Double.valueOf(req.getParameter("price")))
                        .setUser((User) new User().setId(Integer.parseInt(req.getParameter("seller_id"))))
                        .setCar( (Car) new Car()
                                .setFuel(req.getParameter("car_fuel"))
                                .setTransmission(req.getParameter("car_transmission"))
                                .setColor(req.getParameter("car_color"))
                                .setModel(req.getParameter("car_model"))
                                .setYear(Integer.parseInt(req.getParameter("car_year")))
                                .setProducer(req.getParameter("car_producer"))
                                .setId(Integer.parseInt(req.getParameter("car_id"))))
                .setId(Integer.parseInt(req.getParameter("order_id")))

        );
    }
}
