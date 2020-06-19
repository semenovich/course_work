package service.action.orderactions;

import exception.PersistentException;
import service.action.Action;
import service.action.useranduserinfoactions.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchOrderService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Action action = null;
        switch (req.getParameter("search")){
            case "customer_id":
                action = new SearchOrderByCustomerIdService();
                break;
            case "date":
                action = new SearchOrderByDateService();
                break;
            case "price":
                action = new SearchOrderByPriceService();
                break;
            case "seller_id":
                action = new SearchOrderBySellerIdService();
                break;
            case "id":
                action = new SearchOrderByIdService();
                break;
            case "car_id":
                action = new SearchOrderByCarIdService();
                break;
        }
        action.execute(req,resp);
    }
}
