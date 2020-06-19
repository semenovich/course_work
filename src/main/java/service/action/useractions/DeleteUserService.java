package service.action.useractions;

import dao.DAOFactory;
import dao.interfaces.UserDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserService implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userDAO.delete(Integer.parseInt(req.getParameter("user_id")));
    }
}
