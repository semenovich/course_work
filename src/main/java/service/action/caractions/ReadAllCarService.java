package service.action.caractions;

import dao.DAOFactory;
import dao.interfaces.CarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadAllCarService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        req.setAttribute("cars",carDAO.readAll());
    }
}
