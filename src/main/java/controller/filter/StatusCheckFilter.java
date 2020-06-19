package controller.filter;

import bean.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StatusCheckFilter implements Filter {
    private static final Logger logger = Logger.getLogger(StatusCheckFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);

        User user = (User)session.getAttribute("authorizedUser");

        if("registration".equals(req.getParameter("action"))){
            filterChain.doFilter(servletRequest,servletResponse);
        } else if(user.getStatus()){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            logger.warn("Попытка входа заблокированного пользователя");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }

    }
}