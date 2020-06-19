package controller;

import dao.realization.UserInfoDAOImpl;
import exception.PersistentException;
import org.apache.log4j.Logger;
import service.action.Action;
import service.action.ActionName;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(DispatcherServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("action").toUpperCase();
        Action action = ServiceFactory.getInstance().getAction(ActionName.valueOf(actionName));
        try {
            action.execute(req,resp);
        } catch (PersistentException e) {
            logger.error(e.getMessage());
            req.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}
