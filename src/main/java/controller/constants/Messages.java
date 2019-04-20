package controller.constants;
/**
 * Consist messages for sending to front part
 * */
public interface Messages {

    String SCHEDULE_ALREADY_CONFIRMED = "schedule.already.confirmed";
    String SCHEDULE_CONFIRMED = "schedule.confirmed";
    String BUS_ASSIGNED_TO_ROUTE = "bus.assigned.to.route";
    String NO_CONNECTED_DRIVER = "no.connected.driver";
    String DRIVER_ASSIGNED = "driver.assigned";
    String DRIVER_SWAPPED = "drivers.swapped";
    String ROUTE_IS_EMPTY = "route.is.empty";
    String NO_BUSES_TO_APPOINT = "no.buses.to.appoint";
    String NO_DRIVERS_TO_APPOINT = "no.drivers.to.appoint";
    String BUS_CANCELED = "bus.canceled";
    String DRIVER_CANCELED = "driver.canceled";
    String DRIVER_NOT_ASSIGNED = "driver.not.assigned";
    String BUS_HAS_BEEN_DELETED = "bus.deleted";
    String DRIVER_HAS_BEEN_DELETED = "driver.deleted";
    String ROUTE_HAS_BEEN_DELETED = "route.deleted";
    String LANGUAGE_HAS_BEEN_CHANGED = "language.changed";
    String ADMIN_SAVED = "admin.saved";
    String ADMIN_UPDATED = "admin.updated";
    String BUS_SAVED = "bus.saved";
    String BUS_UPDATED = "bus.updated";
    String DRIVER_SAVED = "driver.saved";
    String DRIVER_UPDATED = "driver.updated";
    String ROUTE_SAVED = "route.saved";
    String ROUTE_UPDATED = "route.updated";
    String NO_RESULTS_FOR_CRITERIA = "no.results.for.criteria";
    String INCORRECT_INPUT = "incorrect.input";
    String INPUT_ERROR = "input.error";
    String USER_NOT_REGISTERED = "user.not.registered";
}
