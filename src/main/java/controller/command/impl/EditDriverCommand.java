package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import controller.constants.PathJSP;
import domain.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDriverCommand implements Command {

    private ServiceFactory serviceFactory;
    private DriverService driverService;

    public EditDriverCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        driverService = serviceFactory.getDriverService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idDriver = Integer.valueOf(request.getParameter(FrontConstants.DRIVER_ID));
        Driver driver = driverService.getElementById(idDriver);
        request.setAttribute(FrontConstants.DRIVER, driver);
        return PathJSP.ADD_EDIT_DRIVER_PAGE;
    }
}
