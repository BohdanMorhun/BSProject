package controller.service.impl;

import controller.exception.ServiceLayerException;
import domain.Driver;
import model.dao.impl.DriverDAOImpl;
import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class DriverServiceImplTest {

    private DriverDAOImpl driverDAO;
    private DriverServiceImpl driverService;

    @Before
    public void setUp() throws Exception {
        driverDAO = mock(DriverDAOImpl.class);
        driverService = new DriverServiceImpl(driverDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDriverByUserId() throws DAOException, ServiceLayerException {
        when(driverDAO.getDriverByUserId(anyInt(), any())).thenReturn(new Driver.DriverBuilder().setName("User").createDriver());
        assertEquals("User", driverService.getDriverByUserId(anyInt(), any()).getName());
        verify(driverDAO, atLeast(1)).getDriverByUserId(anyInt(), any());
    }

    @Test
    public void getFreeDrivers() throws DAOException, ServiceLayerException {
        when(driverDAO.getFreeDrivers()).thenReturn(Arrays.asList(new Driver(), new Driver()));
        assertEquals(2, driverService.getFreeDrivers().size());
        verify(driverDAO, atLeast(1)).getFreeDrivers();
    }

    @Test
    public void isElementValid() {
    }
}