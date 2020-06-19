package service.action.userinfoactions;

import bean.User;
import dao.DAOFactory;
import dao.interfaces.UserDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserInfoService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userDAO.update(
                (User) new User()
                .setTelephone(req.getParameter("user_info_telephone"))
                .setEmail(req.getParameter("user_info_email"))
                .setSurname(req.getParameter("user_info_surname"))
                .setName(req.getParameter("user_info_name"))
                .setId(Integer.parseInt(req.getParameter("user_info_id")))
        );
    }
}
