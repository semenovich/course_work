package service.action.useranduserinfoactions;

import bean.User;
import bean.comparator.*;
import dao.DAOFactory;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.sort;

public class CompareUserService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Comparator<User> comparator = null;
        switch (req.getParameter("comparator")){
            case "name":comparator = new CompareUserByName(); break;
            case "surname":comparator = new CompareUserBySurname();break;
            case "email":comparator = new CompareUserByEmail();break;
            case "telephone":comparator = new CompareUserByTelephone();break;
            case "role":comparator = new CompareUserByRole();break;
        }
        UserAndUserInfoDAO dao = DAOFactory.getInstance().getUserAndUserInfoDAO();
        List<User> list = dao.readAll();
        Collections.sort(list,comparator);
        req.setAttribute("users",list);
        req.getRequestDispatcher("/WEB-INF/jsp/user/read.jsp").forward(req,resp);
    }
}
