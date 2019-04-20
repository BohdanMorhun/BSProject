package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.*;
import controller.service.transaction.TransactionManager;
import controller.service.transaction.impl.TransactionManagerImpl;
import domain.*;
import org.apache.log4j.Logger;

/**
 * Base class for processing transaction
 * */
public class BusStationServiceImpl implements BusStationService {

    private ServiceFactory serviceFactory;
    private UserService userService;
    private AdminService adminService;
    private DriverService driverService;
    private BusService busService;
    private RouteService routeService;
    private ScheduleService scheduleService;
    private static final Logger logger = Logger.getLogger(BusStationServiceImpl.class);

    public BusStationServiceImpl() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        userService = serviceFactory.getUserService();
        adminService = serviceFactory.getAdminService();
        driverService = serviceFactory.getDriverService();
        busService = serviceFactory.getBusService();
        routeService = serviceFactory.getRouteService();
        scheduleService = serviceFactory.getScheduleService();
    }

    @Override
    public Driver getDriverAccountDataByUserId(int idUser) throws ServiceLayerException{
        logger.info("Getting driver account info");
        TransactionManager<Driver> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
            Driver driver = driverService.getDriverByUserId(idUser, connection);
            if (driver.getBus().getId() != 0) {
                Bus bus = busService.getElementById(driver.getBus().getId(), connection);
                driver.setBus(bus);
                if (bus.getStatus().equals("work")) {
                    Route route = routeService.getElementById(bus.getRoute().getId(), connection);
                    bus.setRoute(route);
                }
            }
            return driver;
        });
    }

    @Override
    public void deleteRoute(int idRoute) throws ServiceLayerException {
        logger.info("Deleting route from the system");
        TransactionManager transactionManager = new TransactionManagerImpl();
        transactionManager.processVoidTransaction(connection -> {
            Integer busCounter = busService.countBusesOnRouteById(idRoute, connection);
            if (busCounter > 0) {
                routeService.cancelAll(idRoute, connection);
            }
            routeService.deleteElement(idRoute, connection);
        });
    }

    @Override
    public Boolean appointBusToRoute(int idRoute, int idBus) throws ServiceLayerException{
        logger.info("Assigning bus to the route");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
            Driver driver = driverService.getDriverByBusId(idBus, connection);
            if (driver == null) {
                return false;
            }
            Integer busCounter = busService.countBusesOnRouteById(idRoute, connection);
            if (busCounter == 0) {
                routeService.setStatusWork(idRoute, connection);
            }
            busService.appointBusToRoute(idRoute, idBus, connection);
            return true;
        });
    }

    @Override
    public void deleteBus(int idBus) throws ServiceLayerException {
        logger.info("Deleting bus from the system");
        TransactionManager transactionManager = new TransactionManagerImpl();
        transactionManager.processVoidTransaction(connection -> {
            Bus bus = busService.getElementById(idBus, connection);
            Integer idRoute = bus.getRoute().getId();
            if (bus.getStatus().equals("work")) {
                Integer busCounter = busService.countBusesOnRouteById(idRoute, connection);
                if (busCounter == 1) {
                    routeService.setStatusEmpty(idRoute, connection);
                }
                busService.cancelBusFromRoute(idBus, connection);
            }
            driverService.cancelDriverFromBus(idBus, connection);
            busService.deleteElement(idBus, connection);
        });
    }

    @Override
    public Boolean appointDriverToBus(int idBus, int idDriver) throws ServiceLayerException{
        logger.info("Assigning driver to the bus");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
            Driver existingDriver = driverService.getDriverByBusId(idBus, connection);
                if (existingDriver == null) {
                    driverService.updateBusInfoForDriver(idBus, idDriver, connection);
                    return true;
                } else if (existingDriver.getStatus().equals("free")) {
                    driverService.cancelDriverFromBus(idBus, connection);
                    driverService.updateBusInfoForDriver(idBus, idDriver, connection);
                } else if (existingDriver.getStatus().equals("work")) {
                    driverService.cancelDriverFromBus(idBus, connection);
                    driverService.updateBusInfoForDriver(idBus, idDriver, connection);
                    driverService.setStatusNew(idDriver, connection);
                }
            return false;
        });
    }

    @Override
    public void deleteDriver(int idDriver) throws ServiceLayerException {
        logger.info("Deleting bus from the system");
        TransactionManager transactionManager = new TransactionManagerImpl();
        transactionManager.processVoidTransaction(connection -> {
            Driver driver = driverService.getElementById(idDriver, connection);
            if (driver.getStatus().equals("work") || driver.getStatus().equals("new")) {
                Bus bus = busService.getElementById(driver.getBus().getId(), connection);
                Integer idRoute = bus.getRoute().getId();
                Integer busCount = busService.countBusesOnRouteById(idRoute, connection);
                if (busCount == 1) {
                    routeService.setStatusEmpty(idRoute, connection);
                }
                busService.cancelBusFromRoute(driver.getBus().getId(), connection);
            }
            driverService.deleteElement(idDriver, connection);
            userService.deleteElement(driver.getUser().getId(), connection);
        });
    }

    @Override
    public Boolean saveAdmin(Admin admin, User user, String idAdmin, String idUser) throws ServiceLayerException{
        logger.info("Saving admin");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
                if (idUser == null || idUser.isEmpty()) {
                    user.setId(userService.insertElement(user, connection));
                    adminService.insertElement(admin, connection);
                    return true;
                } else {
                    user.setId(Integer.valueOf(idUser));
                    admin.setId(Integer.valueOf(idAdmin));
                    userService.updateElement(user, connection);
                    adminService.updateElement(admin, connection);
                    return false;
                }
        });
    }

    @Override
    public Boolean saveDriver(Driver driver, User user, String idDriver, String idUser) throws ServiceLayerException{
        logger.info("Saving driver");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
                if (idUser == null || idUser.isEmpty()) {
                    user.setId(userService.insertElement(user, connection));
                    driverService.insertElement(driver, connection);
                    return true;
                } else {
                    user.setId(Integer.valueOf(idUser));
                    driver.setId(Integer.valueOf(idDriver));
                    userService.updateElement(user, connection);
                    driverService.updateElement(driver, connection);
                    return false;
                }
        });
    }

    @Override
    public Boolean saveBus(Bus bus, Schedule schedule, String idBus, String idSchedule) throws ServiceLayerException{
        logger.info("Saving bus");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
                if (idBus == null || idBus.isEmpty()) {
                    schedule.setId(scheduleService.insertElement(schedule, connection));
                    busService.insertElement(bus, connection);
                    return true;
                } else {
                    schedule.setId(Integer.valueOf(idSchedule));
                    bus.setId(Integer.valueOf(idBus));
                    scheduleService.updateElement(schedule, connection);
                    busService.updateElement(bus, connection);
                    return false;
                }
        });
    }

    @Override
    public void cancelBus(Integer idBus, Integer idRoute) throws ServiceLayerException {
        logger.info("Cancel bus from the route");
        TransactionManager transactionManager = new TransactionManagerImpl();
        transactionManager.processVoidTransaction(connection -> {
            busService.cancelBusFromRoute(idBus, connection);
            Integer busCounter = busService.countBusesOnRouteById(idRoute, connection);
            if (busCounter == 0) {
                routeService.setStatusEmpty(idRoute, connection);
            }
        });
    }

    @Override
    public Boolean cancelDriver(Integer idBus) throws ServiceLayerException{
        logger.info("Cancel driver from bus");
        TransactionManager<Boolean> transactionManager = new TransactionManagerImpl<>();
        return transactionManager.executeTransaction(connection -> {
            Driver driver = driverService.getDriverByBusId(idBus, connection);
            if (driver == null) {
                return false;
            }
            if (driver.getStatus().equals("free")) {
                driverService.cancelDriverFromBus(idBus, connection);
            } else if (driver.getStatus().equals("work")) {
                Bus bus = busService.getElementById(idBus, connection);
                Integer busCounter = busService.countBusesOnRouteById(bus.getRoute().getId(), connection);
                if (busCounter == 1) {
                    routeService.cancelAll(bus.getRoute().getId(), connection);
                } else {
                    busService.cancelBusFromRoute(idBus, connection);
                }
                driverService.cancelDriverFromBus(idBus, connection);
            }
            return true;
        });
    }
}
