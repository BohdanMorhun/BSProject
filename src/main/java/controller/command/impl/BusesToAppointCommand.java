package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.exception.ServiceLayerException;
import controller.constants.PathJSP;
import controller.service.BusService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesToAppointCommand implements Command {

    private ServiceFactory serviceFactory;
    private BusService busService;

    public BusesToAppointCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        busService = serviceFactory.getBusService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idRoute = request.getParameter(FrontConstants.ROUTE_ID);
        List<Bus> buses = busService.getFreeBuses();
        if (buses.size() == 0){
            request.setAttribute(FrontConstants.MESSAGE,Messages.NO_BUSES_TO_APPOINT);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute(FrontConstants.LIST, buses);
        request.setAttribute(FrontConstants.ROUTE_ID, idRoute);
        return PathJSP.FREE_BUSES_PAGE;
    }
}
