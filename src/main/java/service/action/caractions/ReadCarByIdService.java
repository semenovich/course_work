package service.action.caractions;

import dao.DAOFactory;
import dao.interfaces.CarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadCarByIdService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        req.setAttribute("car",carDAO.read(Integer.parseInt(req.getParameter("car_id"))));
    }
}
