package model.dao.impl;

import model.dao.connection.ConnectionFactory;
import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BusDAOImplTest {

    @Mock
    private ResultSet resultSet;
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    private Connection connection;

    private BusDAOImpl busDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        busDAO = new BusDAOImpl(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseToOneElement() throws SQLException {
        when(resultSet.getInt(any())).thenReturn(1);
        when(resultSet.getString(any())).thenReturn("test");
        assertEquals(new Integer(1), busDAO.parseToOneElement(resultSet).getId());
        assertEquals("test", busDAO.parseToOneElement(resultSet).getModel());
        assertNotNull(busDAO.parseToOneElement(resultSet));
    }

    @Test
    public void countBusesOnRouteById() throws SQLException, DAOException {
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(any())).thenReturn(5);
        assertEquals(new Integer(5), busDAO.countBusesOnRouteById(anyInt(), connection));
    }

    @Test
    public void getBusesByIdRoute() throws SQLException {
        when(resultSet.next()).thenReturn(true);
    }

    @Test
    public void getFreeBuses() {
    }
}