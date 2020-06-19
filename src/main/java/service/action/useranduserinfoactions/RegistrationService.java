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

public class RegistrationService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {

        UserAndUserInfoDAO dao = DAOFactory.getInstance().getUserAndUserInfoDAO();
        dao.create(new User()
                .setRole(UserRole.GUEST)
                .setLogin(req.getParameter("user_login"))
                .setPassword(req.getParameter("user_password"))
                .setTelephone(req.getParameter("user_info_telephone"))
                .setEmail(req.getParameter("user_info_email"))
                .setSurname(req.getParameter("user_info_surname"))
                .setName(req.getParameter("user_info_name"))
        );
        req.setAttribute("success","Пользователь успешно зарегистрирован");
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);

    }
}
