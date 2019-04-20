package controller.service.impl;

import controller.service.*;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;

public class ServiceFactoryImpl implements ServiceFactory {

    private static ServiceFactory serviceFactory;

    private DAOFactory daoFactory;

    private ServiceFactoryImpl() {
        daoFactory = DAOFactoryImpl.getInstance();
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl(daoFactory.getUserDAO());
    }

    @Override
    public AdminService getAdminService() {
        return new AdminServiceImpl(daoFactory.getAdminDAO());
    }

    @Override
    public RouteService getRouteService() {
        return new RouteServiceImpl(daoFactory.getRouteDAO());
    }

    @Override
    public DriverService getDriverService() {
        return new DriverServiceImpl(daoFactory.getDriverDAO());
    }

    @Override
    public BusService getBusService() {
        return new BusServiceImpl(daoFactory.getBusDAO());
    }

    @Override
    public ScheduleService getScheduleService() {
        return new ScheduleServiceImpl(daoFactory.getScheduleDAO());
    }

    public static ServiceFactory getInstance(){
        if (serviceFactory == null){
            synchronized (ServiceFactory.class){
                serviceFactory = new ServiceFactoryImpl();
            }
        }
        return serviceFactory;
    }
}
