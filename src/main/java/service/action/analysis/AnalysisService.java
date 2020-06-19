package service.action.analysis;

import dao.DAOFactory;
import dao.interfaces.AnalysisDAO;
import exception.PersistentException;
import service.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnalysisService implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws PersistentException, ServletException, IOException {
        AnalysisDAO dao = DAOFactory.getInstance().getAnalysisDAO();
        req.setAttribute("expensiveOrder",dao.expensiveCar());
        req.setAttribute("popularColor",dao.popularColor());
        req.setAttribute("popularProducer",dao.popularProducer());
        req.setAttribute("popularFuel",dao.popularFuel());
        req.setAttribute("popularTransmission",dao.popularTransmission());
        req.setAttribute("avgPrice",dao.avgPrice());
        req.setAttribute("avgCarYear",dao.avgCarYear());
        req.setAttribute("activeSeller",dao.activeSeller());
        req.setAttribute("activeCustomer",dao.activeCustomer());

        req.getRequestDispatcher("/WEB-INF/jsp/analysis/analysis.jsp").forward(req,resp);
    }
}
