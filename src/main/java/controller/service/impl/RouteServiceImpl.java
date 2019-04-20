package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.RouteService;
import domain.Route;
import model.dao.RouteDAO;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class RouteServiceImpl extends AbstractGenericService<Route> implements RouteService{

    private RouteDAO routeDAO;
    private static final Logger logger = Logger.getLogger(RouteServiceImpl.class);

    public RouteServiceImpl(RouteDAO routeDAO) {
        super(routeDAO);
        this.routeDAO = routeDAO;
    }

    @Override
    public void setStatusEmpty(int idRoute, Connection connection) throws ServiceLayerException {
        logger.info("Setting status empty for route");
        try {
            routeDAO.setStatusEmpty(idRoute, connection);
        } catch (DAOException e) {
            logger.error("Couldn't set status empty");
            throw new ServiceLayerException("Couldn't set status empty", e);
        }
    }

    @Override
    public void setStatusWork(int idRoute) throws ServiceLayerException {
        logger.info("Setting status work for route");
        try {
            routeDAO.setStatusWork(idRoute);
        } catch (DAOException e) {
            logger.error("Couldn't set status work");
            throw new ServiceLayerException("Couldn't set status work", e);
        }
    }

    @Override
    public void setStatusWork(int idRoute, Connection connection) throws ServiceLayerException {
        logger.info("Setting status work for route");
        try {
            routeDAO.setStatusWork(idRoute, connection);
        } catch (DAOException e) {
            logger.error("Couldn't set status work");
            throw new ServiceLayerException("Couldn't set status work", e);
        }
    }

    @Override
    public void cancelAll(int idRoute, Connection connection) throws ServiceLayerException {
        logger.info("Cancel all buses from route");
        try {
            routeDAO.cancelAll(idRoute, connection);
        } catch (DAOException e) {
            logger.error("Couldn't cancel buses all");
            throw new ServiceLayerException("Couldn't cancel buses all", e);
        }
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) throws ServiceLayerException {
        logger.info("Searching by criteria");
        try {
            return routeDAO.searchByCriteria(departure, arrival);
        } catch (DAOException e) {
            logger.error("Criteria search was failed");
            throw new ServiceLayerException("Criteria search was failed", e);
        }
    }
}
