package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.exception.ServiceLayerException;
import controller.service.RouteService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import controller.constants.PathJSP;
import domain.Route;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRouteCommand implements Command {

    private ServiceFactory serviceFactory;
    private RouteService routeService;

    public EditRouteCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        routeService = serviceFactory.getRouteService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idRoute = request.getParameter(FrontConstants.ROUTE_ID);
        Route route = routeService.getElementById(Integer.parseInt(idRoute));
        request.setAttribute(FrontConstants.ROUTE, route);
        return PathJSP.ADD_EDIT_ROUTE_PAGE;
    }
}
