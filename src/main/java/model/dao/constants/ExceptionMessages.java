package model.dao.constants;

public interface ExceptionMessages {

    String CAN_NOT_EXECUTE_METHOD = "Can't execute method ";

    /*ConnectionFactoryImpl Exception messages*/
    String CAN_NOT_ESTABLISH_CONNECTION = CAN_NOT_EXECUTE_METHOD + "getConnection";
    String CAN_NOT_ROLL_BACK_OPERATION = CAN_NOT_EXECUTE_METHOD + "rollBack";
    String CAN_NOT_CLOSE_CONNECTION = CAN_NOT_EXECUTE_METHOD + "freeConnection";
    String CAN_NOT_INSTANTIATE_CONNECTION_POOL = "Can't create connection pool instance";

    /*AbstractGenericDAO Exception messages*/
    String CAN_NOT_INSERT_ELEMENT = CAN_NOT_EXECUTE_METHOD + "insertElement";
    String CAN_NOT_UPDATE_ELEMENT = CAN_NOT_EXECUTE_METHOD + "updateElement";
    String CAN_NOT_DELETE_ELEMENT = CAN_NOT_EXECUTE_METHOD + "deleteElement";
    String CAN_NOT_GET_ELEMENT_BY_ID = CAN_NOT_EXECUTE_METHOD + "getElementById";
    String CAN_NOT_GET_BY_ID_AND_QUERY = CAN_NOT_EXECUTE_METHOD + "getElementByIdAndQuery";
    String CAN_NOT_GET_PAGINATED_LIST = CAN_NOT_EXECUTE_METHOD + "getPaginatedList";
    String CAN_NOT_GET_ELEMENTS_AMOUNT = CAN_NOT_EXECUTE_METHOD + "getElementsAmount";

    /*AdminDAOImpl Exception messages*/
    String CAN_NOT_GET_ADMIN_DATA_BY_USER_ID = CAN_NOT_EXECUTE_METHOD + "getAdminByUserId";

    /*BusDAOImpl ExceptionMessages*/
    String CAN_NOT_COUNT_BUSES_BY_ID_ROUTE = CAN_NOT_EXECUTE_METHOD + "countBusesByRouteId";
    String CAN_NOT_GET_BUSES_BY_ID_ROUTE = CAN_NOT_EXECUTE_METHOD + "getBusesByIdRoute";
    String CAN_NOT_GET_FREE_BUSES = CAN_NOT_EXECUTE_METHOD + "getFreeBuses";
    String CAN_NOT_CANCEL_BUS_FROM_ROUTE = CAN_NOT_EXECUTE_METHOD + "cancelBusFromRoute";
    String CAN_NOT_APPOINT_BUS_TO_ROUTE = CAN_NOT_EXECUTE_METHOD + "appointBusToRoute";

    /*DriverDAOImpl Exception messages*/
    String CAN_NOT_GET_DRIVER_BY_BUS_ID = CAN_NOT_EXECUTE_METHOD + "getDriverByBusId";
    String CAN_NOT_SET_STATUS_NEW = CAN_NOT_EXECUTE_METHOD + "setStatusNew";
    String CAN_NOT_SET_STATUS_WORK = CAN_NOT_EXECUTE_METHOD + "setStatusWork";
    String CAN_NOT_CANCEL_DRIVER_FROM_BUS = CAN_NOT_EXECUTE_METHOD + "cancelDriverFromBus";
    String CAN_NOT_UPDATE_BUS_INFO_FOR_DRIVER = CAN_NOT_EXECUTE_METHOD + "updateBusInfoForDriver";
    String CAN_NOT_GET_FREE_DRIVERS = CAN_NOT_EXECUTE_METHOD + "getFreeDrivers";

    /*RouteDAOImpl Exception messages*/
    String CAN_NOT_SET_STATUS = CAN_NOT_EXECUTE_METHOD + "setStatus";
    String CAN_NOT_CANCEL_ALL_BUSES_FROM_ROUTE = CAN_NOT_EXECUTE_METHOD + "cancelAll";
    String CAN_NOT_FOUND_ROUTES_BY_CRITERIA = CAN_NOT_EXECUTE_METHOD + "searchByCriteria";

    /*UserDAOImpl Exception messages*/
    String CAN_NOT_FIND_USER_BY_LOGIN_DATA = CAN_NOT_EXECUTE_METHOD + "findUserByLoginData";

}
