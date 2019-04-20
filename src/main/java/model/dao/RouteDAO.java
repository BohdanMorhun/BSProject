package model.dao;

import domain.Route;
import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface RouteDAO extends GenericDAO<Route> {

    void setStatusEmpty(int idRoute, Connection connection) throws DAOException;

    void setStatusWork(int idRoute) throws DAOException;

    void setStatusWork(int idRoute, Connection connection) throws DAOException;

    void cancelAll(int idRoute, Connection connection) throws DAOException;

    List<Route> searchByCriteria(String departure, String arrival) throws DAOException;
}
