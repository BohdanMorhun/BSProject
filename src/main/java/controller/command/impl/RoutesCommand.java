package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.exception.ServiceLayerException;
import controller.service.RouteService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import controller.service.pagination.Carriage;
import controller.service.pagination.PaginationManager;
import controller.constants.PathJSP;
import domain.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoutesCommand implements Command {

    private ServiceFactory serviceFactory;
    private RouteService routeService;

    public RoutesCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        routeService = serviceFactory.getRouteService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String page = request.getParameter(FrontConstants.PAGE);
        String lastPage = request.getParameter(FrontConstants.LAST_PAGE);
        PaginationManager<Route> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, FrontConstants.AMOUNT_PER_PAGE, routeService);
        List<Route> routes = routeService.getPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute(FrontConstants.LAST_PAGE, carriage.getLastPage());
        request.setAttribute(FrontConstants.CURRENT_PAGE, carriage.getCurrentPage());
        request.setAttribute(FrontConstants.LIST, routes);
        return PathJSP.ROUTES_PAGE;
    }
}
