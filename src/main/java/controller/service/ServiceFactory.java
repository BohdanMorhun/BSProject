package controller.service;

public interface ServiceFactory {

    UserService getUserService();

    AdminService getAdminService();

    RouteService getRouteService();

    DriverService getDriverService();

    BusService getBusService();

    ScheduleService getScheduleService();
}
