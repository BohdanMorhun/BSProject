package controller.util.filter;

import controller.constants.FrontConstants;
import controller.util.filter.permission_list.DriverPermissions;
import controller.util.filter.permission_list.UserPermissions;
import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute(FrontConstants.USER);
        String command = servletRequest.getParameter(FrontConstants.COMMAND);
        if (command != null && !command.isEmpty()) {
            try {
                if (user == null) {
                    UserPermissions.valueOf(command.toUpperCase());
                } else if (user.getRole().equals(FrontConstants.DRIVER)) {
                    DriverPermissions.valueOf(command.toUpperCase());
                }
            } catch (IllegalArgumentException exc) {
                servletRequest.setAttribute(FrontConstants.MESSAGE, "access.denied");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
