package service.action.userinfoactions;

import dao.DAOFactory;
import dao.interfaces.UserDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadUserInfoByIdService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        req.setAttribute("userInfo",userDAO.read(Integer.parseInt(req.getParameter("user_info_id"))));
    }
}
