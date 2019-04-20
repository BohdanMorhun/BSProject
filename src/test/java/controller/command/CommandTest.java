package controller.command;


import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandTest {

    @Mock
    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() throws ServiceLayerException, WrongInputDataException {
        when(command.execute(request, response)).thenReturn(PathJSP.INDEX_PAGE);
        assertEquals(PathJSP.INDEX_PAGE, command.execute(request, response));
        assertNotNull(command.execute(request, response));
        verify(command, atLeast(1)).execute(request, response);
    }
}