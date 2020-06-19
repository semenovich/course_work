package service.action.caractions;

import bean.Car;
import dao.DAOFactory;
import dao.interfaces.CarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarWithReturnIdService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        req.setAttribute("carId",carDAO.createWithIdReturn(new Car()
                .setProducer(req.getParameter("car_producer"))
                .setModel(req.getParameter("car_model"))
                .setYear(Integer.parseInt(req.getParameter("car_year")))
                .setColor(req.getParameter("car_color"))
                .setTransmission(req.getParameter("car_transmission"))
                .setFuel(req.getParameter("car_fuel"))
        ));
    }
}
