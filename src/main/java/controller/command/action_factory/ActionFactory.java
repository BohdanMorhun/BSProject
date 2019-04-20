package controller.command.action_factory;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionFactory {

    /**
     * @return special command from CommandList
     * */
    Command defineCommand(HttpServletRequest request, HttpServletResponse response);
}
