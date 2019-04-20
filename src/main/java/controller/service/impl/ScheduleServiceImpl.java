package controller.service.impl;

import controller.service.AbstractGenericService;
import controller.service.ScheduleService;
import domain.Schedule;
import model.dao.ScheduleDAO;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

public class ScheduleServiceImpl extends AbstractGenericService<Schedule> implements ScheduleService {

    public ScheduleServiceImpl(ScheduleDAO scheduleDAO) {
        super(scheduleDAO);
    }
}
