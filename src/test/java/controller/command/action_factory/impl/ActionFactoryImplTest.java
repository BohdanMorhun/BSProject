package controller.command.action_factory.impl;

import controller.command.impl.LoginCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ActionFactoryImplTest {

    @Mock
    private HttpServletRequest request;
    private ActionFactoryImpl actionFactory;
    private HttpServletResponse response;

    @Before
    public void setUp(){
        initMocks(this);
        actionFactory = new ActionFactoryImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void defineCommand() {
        when(request.getParameter(anyString())).thenReturn("login");
        assertEquals("login", request.getParameter(anyString()));
        actionFactory.defineCommand(request, response);
        assertNotNull(actionFactory.defineCommand(request, response));
    }
}