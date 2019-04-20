package model.dao.constants;

public interface DAOConstants {

    interface AdminDBColumns {
        String ADMIN_ID = "admin.idadmin";
        String ADMIN_NAME = "admin.name";
        String ADMIN_SURNAME = "admin.surname";
        String ADMIN_BIRTH = "admin.birth";
        String ADMIN_DEGREE = "admin.degree";
        String ADMIN_GRADUATION = "admin.graduation";
    }

    interface BusDBColumns {
        String BUS_ID = "bus.idbus";
        String BUS_PLATE = "bus.plate";
        String BUS_MODEL = "bus.model";
        String BUS_MILEAGE = "bus.mileage";
        String BUS_INSPECTION = "bus.inspection";
        String BUS_CONSUMPTION = "bus.consumption";
        String BUS_RELEASE_DATE = "bus.release_date";
        String BUS_SEATS = "bus.seats";
        String BUS_STATUS = "bus.status";
        String BUS_ID_ROUTE = "bus.idroute";
    }

    interface DriverDBColumns {
        String DRIVER_ID = "driver.iddriver";
        String DRIVER_NAME = "driver.name";
        String DRIVER_SURNAME = "driver.surname";
        String DRIVER_BIRTH = "driver.birth";
        String DRIVER_LICENSE_TEST = "driver.license_test";
        String DRIVER_SALARY = "driver.salary";
        String DRIVER_STATUS = "driver.status";
        String DRIVER_ID_BUS = "driver.idbus";
    }

    interface RouteDBColumns {
        String ROUTE_ID = "idroute";
        String ROUTE_NUMBER = "route_number";
        String ROUTE_TITLE = "title";
        String ROUTE_DISTANCE = "distance";
        String ROUTE_STATUS = "status";
        String ROUTE_DEPARTURE = "departure";
        String ROUTE_ARRIVAL = "arrival";
    }

    interface ScheduleDBColumns {
        String SCHEDULE_ID = "schedule.idschedule";
        String SCHEDULE_DEPARTURE = "schedule.departure";
        String SCHEDULE_ARRIVAL = "schedule.arrival";
    }

    interface UserDBColumns {
        String USER_ID = "user.iduser";
        String USER_LOGIN = "user.login";
        String USER_PASSWORD = "user.password";
        String USER_ROLE = "user.role";
    }
}
