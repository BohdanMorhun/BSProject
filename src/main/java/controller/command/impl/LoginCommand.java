package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.exception.ServiceLayerException;
import controller.service.ServiceFactory;
import controller.service.UserService;
import controller.service.impl.ServiceFactoryImpl;
import controller.constants.PathJSP;
import domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private ServiceFactory serviceFactory;
    private UserService userService;

    public LoginCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        HttpSession session = request.getSession();
        User user = new User.UserBuilder()
                .setLogin(request.getParameter(FrontConstants.USER_LOGIN))
                .setPassword(request.getParameter(FrontConstants.USER_PASSWORD))
                .createUser();
        User existingUser = userService.findUserByLoginData(user);
        if (user.equals(existingUser)) {
            session.setAttribute(FrontConstants.USER, existingUser);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute(FrontConstants.MESSAGE, Messages.USER_NOT_REGISTERED);
        request.setAttribute(FrontConstants.USER, user);
        return PathJSP.LOGIN_PAGE;
    }
}
