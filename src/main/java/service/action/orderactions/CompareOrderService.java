package service.action.orderactions;

import bean.Order;
import bean.User;
import bean.comparator.*;
import dao.DAOFactory;
import dao.interfaces.OrderDAO;
import dao.interfaces.UserAndUserInfoDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Comparator<Order> comparator = null;
        switch (req.getParameter("comparator")){
            case "date":comparator = new CompareOrderByDate(); break;
            case "price":comparator = new CompareOrderByPrice();break;

        }
        OrderDAO dao = DAOFactory.getInstance().getOrderDAO();
        List<Order> list = dao.readAll();
        Collections.sort(list,comparator);
        req.setAttribute("orders",list);
        req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req,resp);
    }
}
