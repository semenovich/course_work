package service.action.useranduserinfoactions;

import exception.PersistentException;
import service.action.Action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchUserService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Action action = null;
        switch (req.getParameter("search")){
            case "name":
                action = new SearchUserAndUserIfoByNameService();
                break;
            case "surname":
                action = new SearchUserAndUserIfoBySurnameService();
                break;
            case "email":
                action = new SearchUserAndUserIfoByEmailService();
                break;
            case "telephone":
                action = new SearchUserAndUserIfoByTelephoneService();
                break;
            case "role":
                action = new SearchUserAndUserIfoByRoleService();
                break;
        }
        action.execute(req,resp);
    }
}
