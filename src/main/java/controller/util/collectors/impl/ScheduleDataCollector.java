package controller.util.collectors.impl;

import controller.constants.FrontConstants;
import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Schedule;
import org.apache.log4j.Logger;
import resource_manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class ScheduleDataCollector extends DataCollector<Schedule> {

    private static ResourceManager resourceManager = ResourceManager.INSTANCE;

    private static final Logger logger = Logger.getLogger(ScheduleDataCollector.class);

    @Override
    public Schedule retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving schedule data from request");
        int counter = 2;
        String departure = request.getParameter(FrontConstants.DEPARTURE_TIME);
        String arrival = request.getParameter(FrontConstants.ARRIVAL_TIME);
        Schedule schedule = new Schedule();
        if (departure != null && departure.matches(resourceManager.getRegex("reg.time"))){
            schedule.setDeparture(departure);
            counter--;
        }
        if (arrival != null && arrival.matches(resourceManager.getRegex("reg.time"))){
            schedule.setArrival(arrival);
            counter--;
        }
        if (counter != 0){
            logger.warn("Not all input forms filled correctly");
            request.setAttribute(FrontConstants.SCHEDULE, schedule);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return schedule;
    }
}
