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

public class CancelDriverCommand implements Command {

    private BusStationService busStationService;

    public CancelDriverCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idBus = Integer.valueOf(request.getParameter(FrontConstants.BUS_ID));
            if (busStationService.cancelDriver(idBus)){
                request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_CANCELED);
            }else {
                request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_NOT_ASSIGNED);
            }
        return PathJSP.INDEX_PAGE;
    }
}
