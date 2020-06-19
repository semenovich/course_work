package service.action.caractions;

import dao.DAOFactory;
import dao.interfaces.CarDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCarService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        carDAO.delete(Integer.parseInt(req.getParameter("car_id")));
        new ReadAllCarAndOrderService().execute(req,resp);
    }
}
