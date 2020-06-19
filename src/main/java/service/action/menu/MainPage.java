package service.action.menu;

import bean.User;
import dao.realization.UserAndUserInfoDAOImpl;
import exception.PersistentException;
import service.action.Action;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPage implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        req.setAttribute("user", new UserAndUserInfoDAOImpl().read(((User)req.getSession().getAttribute("authorizedUser")).getId()));
        req.getRequestDispatcher((String) req.getSession().getAttribute("mainPage")).forward(req, resp);
    }
}
