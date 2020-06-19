package controller.filter;

import bean.User;
import dao.DAOFactory;
import exception.PersistentException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(AuthenticationFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        String login = req.getParameter("user_login");
        String password = req.getParameter("user_password");

        User user = null;
        try {
            user = DAOFactory.getInstance().getUserAndUserInfoDAO().read(login,password);
        } catch (PersistentException e) {
            logger.error(e.getMessage());
        }

        if(session.getAttribute("authorizedUser") != null
                || "registration".equals(req.getParameter("action"))
        ) {
            filterChain.doFilter(servletRequest,servletResponse);

        }else if(user != null ){
            session.setAttribute("authorizedUser",user);
            String urlMainPage = null;

            switch (user.getRole().toString()){
                case "ADMIN" :  urlMainPage = "/WEB-INF/jsp/menu/admin.jsp"; break;
                case "SELLER" : urlMainPage = "/WEB-INF/jsp/menu/seller.jsp"; break;
                case "PERSONNALOFFICER" : urlMainPage = "/WEB-INF/jsp/menu/personalOfficer.jsp"; break;
                case "GUEST" :urlMainPage = "/WEB-INF/jsp/menu/guest.jsp"; break;
            }
            session.setAttribute("mainPage",urlMainPage);
            logger.error("Вход "+user.getName());
            filterChain.doFilter(servletRequest,servletResponse);

        }else {
            logger.error("Попытка входа");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }

    }
}
