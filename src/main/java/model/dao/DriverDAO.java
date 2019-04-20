package model.dao;

import domain.Driver;
import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface DriverDAO extends GenericDAO<Driver> {

    Driver getDriverByUserId(int idUser, Connection connection) throws DAOException;

    Driver getDriverByBusId(int idBus) throws DAOException;

    Driver getDriverByBusId(int idBus, Connection connection) throws DAOException;

    void setStatusNew(int idDriver, Connection connection) throws DAOException;

    void setStatusWork(int idDriver) throws DAOException;

    void cancelDriverFromBus(int idBus, Connection connection) throws DAOException;

    void updateBusInfoForDriver(int idBus, int idDriver, Connection connection) throws DAOException;

    List<Driver> getFreeDrivers() throws DAOException;
}
