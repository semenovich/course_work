package service.action.orderactions;

import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SearchOrderByDateService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        OrderDAO dao = DAOFactory.getInstance().getOrderDAO();
        String[] strings = req.getParameter("value").split("-");
        for (String string:strings) {
            System.out.println(string);
        }
        System.out.println(strings);
        req.setAttribute("orders",dao.searchByDate(
                new Date(Integer.parseInt(strings[0])-1900,
                        Integer.parseInt(strings[1])-1,
                                Integer.parseInt(strings[2])+1)));
        req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req,resp);
    }
}
