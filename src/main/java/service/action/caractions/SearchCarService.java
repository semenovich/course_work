package service.action.caractions;

import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchCarService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        Action action = null;
        switch (req.getParameter("search")){
            case "producer":action = new SearchCarByProducerService();break;
            case "model":action = new SearchCarByModelService();break;
            case "year":action = new SearchCarByYearService();break;
            case "color":action = new SearchCarByColorService();break;
            case "transmission":action = new SearchCarByTransmissionService();break;
            case "fuel":action = new SearchCarByFuelService();break;
        }
        action.execute(req,resp);
    }
}
