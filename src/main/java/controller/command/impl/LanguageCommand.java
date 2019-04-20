package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter(FrontConstants.LANGUAGE);
        if (language.equals(FrontConstants.ENGLISH)){
            session.setAttribute(FrontConstants.LANGUAGE, FrontConstants.EN_US);
        }else if (language.equals(FrontConstants.UKRAINIAN)){
            session.setAttribute(FrontConstants.LANGUAGE,FrontConstants.UK_UA);
        }else {
            session.setAttribute(FrontConstants.LANGUAGE, FrontConstants.RU_RU);
        }
        request.setAttribute(FrontConstants.MESSAGE, Messages.LANGUAGE_HAS_BEEN_CHANGED);
        return PathJSP.INDEX_PAGE;
    }
}
