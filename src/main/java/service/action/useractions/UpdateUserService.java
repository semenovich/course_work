package service.action.useractions;

import bean.User;
import bean.UserRole;
import dao.DAOFactory;
import dao.interfaces.UserDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userDAO.update(
                (User) new User()
                        .setRole(UserRole.valueOf(req.getParameter("user_role")))
                        .setLogin(req.getParameter("user_login"))
                        .setPassword(req.getParameter("user_password"))
                        .setId(Integer.parseInt(req.getParameter("user_id")))
        );
    }
}
