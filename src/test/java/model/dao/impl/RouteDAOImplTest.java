package model.dao.impl;

import model.dao.connection.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RouteDAOImplTest {

    @Mock
    private ResultSet resultSet;
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    private Connection connection;

    private RouteDAOImpl routeDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        routeDAO = new RouteDAOImpl(connectionFactory);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseToOneElement() throws SQLException {
        when(resultSet.getInt(any())).thenReturn(3);
        when(resultSet.getString(any())).thenReturn("test");
        assertEquals(new Integer(3), routeDAO.parseToOneElement(resultSet).getId());
        assertEquals("test", routeDAO.parseToOneElement(resultSet).getTitle());
        assertNotNull(routeDAO.parseToOneElement(resultSet));
    }

    @Test
    public void searchByCriteria() {

    }
}