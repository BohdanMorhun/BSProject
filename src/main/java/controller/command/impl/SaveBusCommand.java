package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.service.impl.BusStationServiceImpl;
import controller.util.collectors.impl.BusDataCollector;
import domain.Bus;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;

public class SaveBusCommand implements Command {

    private BusStationService busStationService;
    private static final Logger logger = Logger.getLogger(SaveBusCommand.class);

    public SaveBusCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveBusCommand");
        String idBus = request.getParameter(FrontConstants.BUS_ID);
        String idSchedule = request.getParameter(FrontConstants.SCHEDULE_ID);
        try {
            Bus bus = new BusDataCollector().retrieveData(request);
            if (busStationService.saveBus(bus, bus.getSchedule(), idBus, idSchedule)){
                request.setAttribute(FrontConstants.MESSAGE, Messages.BUS_SAVED);
            }else {
                request.setAttribute(FrontConstants.MESSAGE, Messages.BUS_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute(FrontConstants.MESSAGE, Messages.INPUT_ERROR);
            return PathJSP.ADD_EDIT_BUS_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}
