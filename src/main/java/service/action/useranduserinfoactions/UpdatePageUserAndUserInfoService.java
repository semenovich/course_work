package service.action.useranduserinfoactions;

import dao.DAOFactory;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdatePageUserAndUserInfoService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        new ReadUserAndUserInfoByIdService().execute(req,resp);
        req.getRequestDispatcher("/WEB-INF/jsp/user/update.jsp").forward(req,resp);
    }
}
