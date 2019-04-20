package controller.command;

import controller.command.impl.*;

/**
 *Enum which contains all command for application*/
public enum CommandList {

    EMPTY(new EmptyCommand()),
    LOGIN(new LoginCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    LOGOUT_PAGE(new LogOutCommand()),
    LANGUAGE(new LanguageCommand()),
    ABOUT(new AboutResourceCommand()),
    ACCOUNT(new AccountCommand()),
    CONTACTS(new ContactsCommand()),
    SEARCH(new SearchCommand()),


    BUSES(new BusesCommand()),
    DRIVERS(new DriversCommand()),
    ROUTES(new RoutesCommand()),

    /*Route menu commands*/
    BUSES_INFO(new BusesInfoCommand()),
    BUSES_TO_APPOINT(new BusesToAppointCommand()),
    EDIT_ROUTE(new EditRouteCommand()),
    DELETE_ROUTE(new DeleteRouteCommand()),

    APPOINT_BUS(new AppointBusCommand()),

    /*Bus menu commands*/
    DRIVER_INFO(new DriverInfoCommand()),
    FREE_DRIVERS(new FreeDriversCommand()),
    EDIT_BUS(new EditBusCommand()),
    DELETE_BUS(new DeleteBusCommand()),

    APPOINT_DRIVER(new AppointDriverCommand()),

    /*Driver menu commands*/
    EDIT_DRIVER(new EditDriverCommand()),
    EDIT_DRIVER_ACCOUNT(new EditDriverCommand()),
    DELETE_DRIVER(new DeleteDriverCommand()),

    ADD_BUS(new AddBusCommand()),
    ADD_ROUTE(new AddRouteCommand()),
    ADD_DRIVER(new AddDriverCommand()),
    SAVE_ADMIN(new SaveAdminCommand()),
    SAVE_BUS(new SaveBusCommand()),
    SAVE_DRIVER(new SaveDriverCommand()),
    SAVE_ROUTE(new SaveRouteCommand()),
    CANCEL_BUS(new CancelBusCommand()),
    CANCEL_DRIVER(new CancelDriverCommand()),

    ACCEPT_SCHEDULE(new AcceptSchedule());

    Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
