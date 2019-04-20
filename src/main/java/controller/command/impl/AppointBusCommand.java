package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.service.impl.BusStationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointBusCommand implements Command {

    private BusStationService busStationService;

    public AppointBusCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idRoute = Integer.valueOf(request.getParameter(FrontConstants.ROUTE_ID));
        Integer idBus = Integer.valueOf(request.getParameter(FrontConstants.BUS_ID));
        if (busStationService.appointBusToRoute(idRoute, idBus)) {
            request.setAttribute(FrontConstants.MESSAGE, Messages.BUS_ASSIGNED_TO_ROUTE);
        } else {
            request.setAttribute(FrontConstants.MESSAGE, Messages.NO_CONNECTED_DRIVER);
        }
        return PathJSP.INDEX_PAGE;
    }
}
