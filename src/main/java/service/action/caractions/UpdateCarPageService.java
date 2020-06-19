package service.action.caractions;

import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCarPageService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        new ReadCarByIdService().execute(req,resp);
        req.getRequestDispatcher("/WEB-INF/jsp/car/update.jsp").forward(req,resp);
    }
}
