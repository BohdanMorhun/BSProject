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

public class DeleteBusCommand implements Command {

    private BusStationService busStationService;

    public DeleteBusCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idBus = Integer.valueOf(request.getParameter(FrontConstants.BUS_ID));
        busStationService.deleteBus(idBus);
        request.setAttribute(FrontConstants.MESSAGE, Messages.BUS_HAS_BEEN_DELETED);
        return PathJSP.INDEX_PAGE;
    }
}
