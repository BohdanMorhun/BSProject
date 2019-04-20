package controller.command;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {

    /**
     * @return path to appropriate jsp page
     * */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException;
}
