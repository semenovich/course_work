package service.action.orderactions;

import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoreOrderInfo implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        new ReadOrderByIdService().execute(req,resp);
        req.getRequestDispatcher("/WEB-INF/jsp/order/order.jsp").forward(req,resp);
    }
}
