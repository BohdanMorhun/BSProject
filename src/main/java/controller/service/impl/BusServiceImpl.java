package controller.service.impl;

import controller.constants.LogMessages;
import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.BusService;
import controller.service.ScheduleService;
import domain.Bus;
import domain.Schedule;
import model.dao.BusDAO;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class BusServiceImpl extends AbstractGenericService<Bus> implements BusService {

    private BusDAO busDAO;
    private static final Logger logger = Logger.getLogger(BusServiceImpl.class);

    public BusServiceImpl(BusDAO busDAO) {
        super(busDAO);
        this.busDAO = busDAO;
    }


    @Override
    public List<Bus> getFreeBuses() throws ServiceLayerException {
        logger.info(LogMessages.GET_FREE_BUSES);
        try {
            return busDAO.getFreeBuses();
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_FREE_BUSES);
            throw new ServiceLayerException(LogMessages.CAN_NOT_GET_FREE_BUSES, e);
        }
    }

    @Override
    public Integer countBusesOnRouteById(int idRoute, Connection connection) throws ServiceLayerException {
        logger.info("Counting buses on route");
        try {
            return busDAO.countBusesOnRouteById(idRoute, connection);
        } catch (DAOException e) {
            logger.error("Couldn't count buses");
            throw new ServiceLayerException("Couldn't count buses", e);
        }
    }

    @Override
    public void cancelBusFromRoute(int idBus, Connection connection) throws ServiceLayerException {
        logger.info("Try cancel bus from route");
        try {
            busDAO.cancelBusFromRoute(idBus, connection);
        } catch (DAOException e) {
            logger.error("Couldn't cancel bus");
            throw new ServiceLayerException("Couldn't cancel bus", e);
        }
    }

    @Override
    public void appointBusToRoute(int idRoute, int idBus, Connection connection) throws ServiceLayerException {
        logger.info("Assigning appoint bus to route");
        try {
            busDAO.appointBusToRoute(idRoute, idBus, connection);
        } catch (DAOException e) {
            logger.error("Couldn't assign bus to the route");
            throw new ServiceLayerException("Couldn't assign bus to the route", e);
        }
    }

    @Override
    public List<Bus> getBusesByIdRoute(int idRoute) throws ServiceLayerException {
        logger.info("Getting buses by route id");
        try {
            return busDAO.getBusesByIdRoute(idRoute);
        } catch (DAOException e) {
            logger.error("Couldn't get buses");
            throw new ServiceLayerException("Couldn't get buses", e);
        }
    }
}
