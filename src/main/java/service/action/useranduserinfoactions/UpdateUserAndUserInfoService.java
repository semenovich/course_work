package service.action.useranduserinfoactions;

import bean.User;
import bean.UserRole;
import dao.DAOFactory;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserAndUserInfoService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        UserAndUserInfoDAO dao = DAOFactory.getInstance().getUserAndUserInfoDAO();
        dao.update(
                (User) new User()
                        .setRole(UserRole.valueOf(req.getParameter("user_role")))
                        .setLogin(req.getParameter("user_login"))
                        .setPassword(req.getParameter("user_password"))
                        .setTelephone(req.getParameter("user_info_telephone"))
                        .setEmail(req.getParameter("user_info_email"))
                        .setSurname(req.getParameter("user_info_surname"))
                        .setName(req.getParameter("user_info_name"))
                        .setId(Integer.parseInt(req.getParameter("user_id")))
        );
        new ReadAllUserAndUserInfoService().execute(req,resp);
    }
}
