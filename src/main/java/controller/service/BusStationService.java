package controller.service;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import domain.*;

public interface BusStationService {

    Boolean saveAdmin(Admin admin, User user, String idAdmin, String idUser) throws ServiceLayerException;

    Driver getDriverAccountDataByUserId(int idUser) throws ServiceLayerException;

    Boolean cancelDriver(Integer idBus) throws ServiceLayerException;

    Boolean appointDriverToBus(int idBus, int idDriver) throws ServiceLayerException;

    Boolean saveDriver(Driver driver, User user, String idDriver, String idUser) throws ServiceLayerException;

    void deleteDriver(int idDriver) throws ServiceLayerException;

    void deleteRoute(int idRoute) throws ServiceLayerException;

    Boolean appointBusToRoute(int idRoute, int idBus) throws ServiceLayerException;

    void deleteBus(int idBus) throws ServiceLayerException;

    Boolean saveBus(Bus bus, Schedule schedule, String idBus, String idSchedule) throws ServiceLayerException;

    void cancelBus(Integer idBus, Integer idRoute) throws ServiceLayerException;
}
