package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;
import controller.service.impl.BusStationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverCommand implements Command {

    private BusStationService busStationService;

    public DeleteDriverCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idDriver = Integer.valueOf(request.getParameter(FrontConstants.DRIVER_ID));
        busStationService.deleteDriver(idDriver);
        request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_HAS_BEEN_DELETED);
        return PathJSP.INDEX_PAGE;
    }
}
