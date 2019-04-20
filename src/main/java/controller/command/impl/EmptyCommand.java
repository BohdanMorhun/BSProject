package controller.command.impl;

import controller.command.Command;
import controller.constants.PathJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PathJSP.INDEX_PAGE;
    }
}
