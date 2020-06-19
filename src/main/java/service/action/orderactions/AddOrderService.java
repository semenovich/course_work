package service.action.orderactions;

import exception.PersistentException;
import service.action.Action;
import service.action.caractions.AddCarWithReturnIdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Action action = null;
        switch (req.getParameter("isBought")){
            case "yes":action = new AddOrderWithoutCustomerService();break;
            case "no":action = new AddOrderWithCustomerService();break;
        }
        action.execute(req,resp);
    }
}
