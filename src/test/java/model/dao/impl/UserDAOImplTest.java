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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDAOImplTest {

    @Mock
    private ResultSet resultSet;
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;

    private UserDAOImpl userDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        userDAO = new UserDAOImpl(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseToOneElement() throws SQLException {
        when(resultSet.getInt(any())).thenReturn(3);
        when(resultSet.getString(any())).thenReturn("test");
        assertEquals(new Integer(3), userDAO.parseToOneElement(resultSet).getId());
        assertEquals("test", userDAO.parseToOneElement(resultSet).getLogin());
        assertNotNull(userDAO.parseToOneElement(resultSet));
    }

    @Test
    public void findUserByLoginData() throws SQLException, DAOException {
/*        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(any())).thenReturn(4);
        assertEquals(new Integer(4), userDAO.findUserByLoginData(any()).getId());*/
    }
}