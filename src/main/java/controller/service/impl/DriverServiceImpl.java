package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.DriverService;
import controller.service.UserService;
import domain.Driver;
import domain.User;
import model.dao.DriverDAO;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class DriverServiceImpl extends AbstractGenericService<Driver> implements DriverService {

    private DriverDAO driverDAO;
    private static final Logger logger = Logger.getLogger(DriverServiceImpl.class);

    public DriverServiceImpl(DriverDAO driverDAO) {
        super(driverDAO);
        this.driverDAO = driverDAO;
    }

    @Override
    public Driver getDriverByUserId(int idUser, Connection connection) throws ServiceLayerException {
        logger.info("Getting driver by user id");
        try {
            return driverDAO.getDriverByUserId(idUser, connection);
        } catch (DAOException e) {
            logger.error("Couldn't get driver by user id");
            throw new ServiceLayerException("Couldn't get driver by user id", e);
        }
    }

    @Override
    public Driver getDriverByBusId(int idBus) throws ServiceLayerException {
        logger.info("Getting driver by bus id");
        try {
            return driverDAO.getDriverByBusId(idBus);
        } catch (DAOException e) {
            logger.error("Couldn't get driver by bus id");
            throw new ServiceLayerException("Couldn't get driver by bus id", e);
        }
    }

    @Override
    public Driver getDriverByBusId(int idBus, Connection connection) throws ServiceLayerException {
        logger.info("Getting driver by bus id");
        try {
            return driverDAO.getDriverByBusId(idBus, connection);
        } catch (DAOException e) {
            logger.error("Couldn't get driver by bus id");
            throw new ServiceLayerException("Couldn't get driver by bus id", e);
        }
    }

    @Override
    public void setStatusNew(int idDriver, Connection connection) throws ServiceLayerException {
        logger.info("Setting status new for driver");
        try {
            driverDAO.setStatusNew(idDriver, connection);
        } catch (DAOException e) {
            logger.error("Couldn't set status new for driver");
            throw new ServiceLayerException("Couldn't set status new for driver", e);
        }
    }

    @Override
    public void setStatusWork(int idDriver) throws ServiceLayerException {
        logger.info("Setting status work for driver");
        try {
            driverDAO.setStatusWork(idDriver);
        } catch (DAOException e) {
            logger.error("Couldn't set status work for driver");
            throw new ServiceLayerException("Couldn't set status work for driver", e);
        }
    }

    @Override
    public void cancelDriverFromBus(int idBus, Connection connection) throws ServiceLayerException {
        logger.info("Cancel driver from bus");
        try {
            driverDAO.cancelDriverFromBus(idBus, connection);
        } catch (DAOException e) {
            logger.error("Couldn't cancel driver from bus");
            throw new ServiceLayerException("Couldn't cancel driver from bus", e);
        }
    }

    @Override
    public List<Driver> getFreeDrivers() throws ServiceLayerException {
        logger.info("Getting free drivers");
        try {
            return driverDAO.getFreeDrivers();
        } catch (DAOException e) {
            logger.error("Couldn't get free drivers");
            throw new ServiceLayerException("Couldn't get free drivers", e);
        }
    }

    @Override
    public void updateBusInfoForDriver(int idBus, int idDriver, Connection connection) throws ServiceLayerException {
        logger.info("Assigning bus for driver");
        try {
            driverDAO.updateBusInfoForDriver(idBus, idDriver, connection);
        } catch (DAOException e) {
            logger.error("Couldn't assign bus");
            throw new ServiceLayerException("Couldn't assign bus", e);
        }
    }
}
