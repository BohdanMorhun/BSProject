package controller.service;

import controller.exception.ServiceLayerException;
import domain.Driver;

import java.sql.Connection;
import java.util.List;

public interface DriverService extends GenericService<Driver> {

    Driver getDriverByUserId(int idUser, Connection connection) throws ServiceLayerException;

    Driver getDriverByBusId(int idBus) throws ServiceLayerException;

    Driver getDriverByBusId(int idBus, Connection connection) throws ServiceLayerException;

    void setStatusNew(int idDriver, Connection connection) throws ServiceLayerException;

    void setStatusWork(int idDriver) throws ServiceLayerException;

    void cancelDriverFromBus(int idBus, Connection connection) throws ServiceLayerException;

    void updateBusInfoForDriver(int idBus, int idDriver, Connection connection) throws ServiceLayerException;

    List<Driver> getFreeDrivers() throws ServiceLayerException;
}
