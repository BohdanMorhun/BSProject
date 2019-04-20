package controller.service.impl;

import controller.exception.ServiceLayerException;
import domain.Route;
import model.dao.impl.RouteDAOImpl;
import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RouteServiceImplTest {

    private RouteDAOImpl routeDAO;

    private RouteServiceImpl routeService;

    @Before
    public void setUp() throws Exception {
        routeDAO = mock(RouteDAOImpl.class);
        routeService = new RouteServiceImpl(routeDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchByCriteria() throws DAOException, ServiceLayerException {
        when(routeDAO.searchByCriteria(anyString(), anyString())).thenReturn(Arrays.asList(new Route(), new Route()));
        assertEquals(2, routeService.searchByCriteria(anyString(), anyString()).size());
        verify(routeDAO, atLeast(1)).searchByCriteria(anyString(), anyString());
    }

    @Test
    public void isElementValid() {
    }
}