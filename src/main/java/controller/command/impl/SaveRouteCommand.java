package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.RouteService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import controller.util.collectors.impl.RouteDataCollector;
import domain.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveRouteCommand implements Command {

    private ServiceFactory serviceFactory;
    private RouteService routeService;
    private static final Logger logger = Logger.getLogger(SaveRouteCommand.class);

    public SaveRouteCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        routeService = serviceFactory.getRouteService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveRouteCommand");
        String idRoute = request.getParameter(FrontConstants.ROUTE_ID);
        try {
            Route route = new RouteDataCollector().retrieveData(request);
            if (idRoute == null || idRoute.isEmpty()){
                routeService.insertElement(route);
                request.setAttribute(FrontConstants.MESSAGE, Messages.ROUTE_SAVED);
            }else {
                route.setId(Integer.valueOf(idRoute));
                routeService.updateElement(route);
                request.setAttribute(FrontConstants.MESSAGE, Messages.ROUTE_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data.", e);
            request.setAttribute(FrontConstants.MESSAGE, Messages.INPUT_ERROR);
            return PathJSP.ADD_EDIT_ROUTE_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}
