package service.action.useranduserinfoactions;

import dao.DAOFactory;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchUserAndUserIfoByNameService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        UserAndUserInfoDAO dao = DAOFactory.getInstance().getUserAndUserInfoDAO();
        req.setAttribute("users",dao.searchByName(req.getParameter("value")));
        req.getRequestDispatcher("/WEB-INF/jsp/user/read.jsp").forward(req, resp);
    }
}
