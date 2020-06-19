package service.action.caractions;

import bean.Car;
import dao.DAOFactory;
import dao.interfaces.CarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCarService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        carDAO.create(
                (Car) new Car()
                .setProducer(req.getParameter("car_producer"))
                .setModel(req.getParameter("car_model"))
                .setYear(Integer.parseInt(req.getParameter("car_year")))
                .setColor(req.getParameter("car_color"))
                .setTransmission(req.getParameter("car_transmission"))
                .setFuel(req.getParameter("car_fuel"))
                .setId(Integer.parseInt(req.getParameter("car_car_id")))
        );
        new ReadAllCarAndOrderService().execute(req,resp);
    }
}
