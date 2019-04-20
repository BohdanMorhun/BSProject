package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.exception.ServiceLayerException;
import controller.service.BusService;
import controller.service.ServiceFactory;
import controller.service.impl.ServiceFactoryImpl;
import controller.service.pagination.Carriage;
import controller.service.pagination.PaginationManager;
import controller.constants.PathJSP;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesCommand implements Command {

    private ServiceFactory serviceFactory;
    private BusService busService;

    public BusesCommand() {
        serviceFactory = ServiceFactoryImpl.getInstance();
        busService = serviceFactory.getBusService();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String page = request.getParameter(FrontConstants.PAGE);
        String lastPage = request.getParameter(FrontConstants.LAST_PAGE);
        PaginationManager<Bus> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, FrontConstants.AMOUNT_PER_PAGE, busService);
        List<Bus> buses = busService.getPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute(FrontConstants.LAST_PAGE, carriage.getLastPage());
        request.setAttribute(FrontConstants.CURRENT_PAGE, carriage.getCurrentPage());
        request.setAttribute(FrontConstants.LIST, buses);
        return PathJSP.BUSES_PAGE;
    }
}
