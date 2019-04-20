package controller.service;

import controller.exception.ServiceLayerException;
import domain.Bus;

import java.sql.Connection;
import java.util.List;

public interface BusService extends GenericService<Bus>{

    List<Bus> getFreeBuses() throws ServiceLayerException;

    Integer countBusesOnRouteById(int idRoute, Connection connection) throws ServiceLayerException;

    void cancelBusFromRoute(int idBus, Connection connection) throws ServiceLayerException;

    void appointBusToRoute(int idRoute, int idBus, Connection connection) throws ServiceLayerException;

    List<Bus> getBusesByIdRoute(int idRoute) throws ServiceLayerException;
}
