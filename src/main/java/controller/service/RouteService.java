package controller.service;

import controller.exception.ServiceLayerException;
import domain.Route;

import java.sql.Connection;
import java.util.List;

public interface RouteService extends GenericService<Route>{

    void setStatusEmpty(int idRoute, Connection connection) throws ServiceLayerException;

    void setStatusWork(int idRoute) throws ServiceLayerException;

    void setStatusWork(int idRoute, Connection connection) throws ServiceLayerException;

    void cancelAll(int idRoute, Connection connection) throws ServiceLayerException;

    List<Route> searchByCriteria(String departure, String arrival) throws ServiceLayerException;
}
