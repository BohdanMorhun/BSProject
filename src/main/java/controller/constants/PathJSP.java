package controller.constants;

/**
 * Path to jsp
 * */
public interface PathJSP {

    /*General pages*/
    String INDEX_PAGE = "index.jsp";
    String ERROR_PAGE = "error.jsp";
    String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";
    String CONTACTS_PAGE = "WEB-INF/jsp/contacts.jsp";

    /*Admin pages*/
    String ADMIN_ACCOUNT_PAGE = "WEB-INF/jsp/admin/account.jsp";
    String BUSES_ON_ROUTE = "WEB-INF/jsp/admin/buses_on_route.jsp";

    /*Driver pages*/
    String DRIVER_ACCOUNT_PAGE = "WEB-INF/jsp/driver/account.jsp";
    String FREE_DRIVERS_PAGE = "WEB-INF/jsp/admin/free_drivers.jsp";
    String FREE_BUSES_PAGE = "WEB-INF/jsp/admin/free_buses.jsp";

    /*Editing pages*/
    String ADD_EDIT_BUS_PAGE = "WEB-INF/jsp/editing_pages/add_bus.jsp";
    String ADD_EDIT_DRIVER_PAGE = "WEB-INF/jsp/editing_pages/add_driver.jsp";
    String ADD_EDIT_ROUTE_PAGE = "WEB-INF/jsp/editing_pages/add_route.jsp";

    /*Common for driver and admin*/
    String BUSES_PAGE = "WEB-INF/jsp/bus.jsp";
    String DRIVERS_PAGE = "WEB-INF/jsp/driver.jsp";
    String ROUTES_PAGE = "WEB-INF/jsp/route.jsp";

}
