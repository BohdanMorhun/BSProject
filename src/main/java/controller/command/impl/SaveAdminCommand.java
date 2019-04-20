package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.service.impl.BusStationServiceImpl;
import controller.util.collectors.impl.AdminDataCollector;
import domain.Admin;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class SaveAdminCommand implements Command {

    private BusStationService busStationService;
    private static final Logger logger = Logger.getLogger(SaveAdminCommand.class);

    public SaveAdminCommand() {
        busStationService = new BusStationServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveAdminCommand");
        String idAdmin = request.getParameter(FrontConstants.ADMIN_ID);
        String idUser = request.getParameter(FrontConstants.USER_ID);
        try {
            Admin admin = new AdminDataCollector().retrieveData(request);
            if (busStationService.saveAdmin(admin, admin.getUser(), idAdmin, idUser)){
                request.setAttribute(FrontConstants.MESSAGE, Messages.ADMIN_SAVED);
            }else {
                request.setAttribute(FrontConstants.MESSAGE, Messages.ADMIN_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute(FrontConstants.MESSAGE, Messages.INPUT_ERROR);
            return PathJSP.ADMIN_ACCOUNT_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}
