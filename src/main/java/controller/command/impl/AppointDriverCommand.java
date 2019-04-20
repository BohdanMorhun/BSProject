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

public class AppointDriverCommand implements Command {

    private BusStationService busStationService;

    public AppointDriverCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idDriver = Integer.valueOf(request.getParameter(FrontConstants.DRIVER_ID));
        Integer idBus = Integer.valueOf(request.getParameter(FrontConstants.BUS_ID));
        if (busStationService.appointDriverToBus(idBus, idDriver)) {
            request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_ASSIGNED);
        } else {
            request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_SWAPPED);
        }
        return PathJSP.INDEX_PAGE;
    }
}
