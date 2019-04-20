package controller;

import controller.command.Command;
import controller.command.action_factory.ActionFactory;
import controller.command.action_factory.impl.ActionFactoryImpl;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.constants.PathJSP;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Application controller
 * */
public class  MainController extends HttpServlet {

    private static ActionFactory factory = new ActionFactoryImpl();
    private static Logger logger = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Main controller, processing request.");
        Command command = factory.defineCommand(request, response);
        String page = null;
        try {
            logger.info("Executing command");
            page = command.execute(request, response);
            if (page != null) {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (ServiceLayerException e) {
            logger.error(e);
            request.getRequestDispatcher(PathJSP.ERROR_PAGE).forward(request, response);
        }
    }
}
