package controller.command.action_factory.impl;

import controller.command.Command;
import controller.command.CommandList;
import controller.command.action_factory.ActionFactory;
import controller.command.impl.EmptyCommand;
import controller.constants.FrontConstants;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactoryImpl implements ActionFactory{

    private static final Logger logger = Logger.getLogger(ActionFactoryImpl.class);

    @Override
    public Command defineCommand(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ActionFactory defining command");
        Command current = new EmptyCommand();
        String action = request.getParameter(FrontConstants.COMMAND);
        logger.info("Command is: " + action.toUpperCase());
        String message = (String) request.getAttribute(FrontConstants.MESSAGE);
        if (action == null || action.isEmpty() || message != null){
            return current;
        }
        try {
            CommandList commandList = CommandList.valueOf(action.toUpperCase());
            current = commandList.getCommand();
        }catch (IllegalArgumentException e){
            logger.error("Couldn't define command", e);
        }
        return current;
    }
}
