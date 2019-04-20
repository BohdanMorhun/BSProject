package model.dao;

import domain.Bus;
import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface BusDAO extends GenericDAO<Bus> {

    List<Bus> getFreeBuses() throws DAOException;

    Integer countBusesOnRouteById(int idRoute, Connection connection) throws DAOException;

    void cancelBusFromRoute(int idBus, Connection connection) throws DAOException;

    void appointBusToRoute(int idRoute, int idBus, Connection connection) throws DAOException;

    List<Bus> getBusesByIdRoute(int idRoute) throws DAOException;
}
