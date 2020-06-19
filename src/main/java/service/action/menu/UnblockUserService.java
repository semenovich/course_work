package service.action.menu;

import dao.DAOFactory;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;
import service.action.useranduserinfoactions.ReadAllUserAndUserInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnblockUserService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        UserAndUserInfoDAO dao = DAOFactory.getInstance().getUserAndUserInfoDAO();
        dao.unblock(Integer.parseInt(req.getParameter("user_id")));
        new ReadAllUserAndUserInfoService().execute(req,resp);
    }
}