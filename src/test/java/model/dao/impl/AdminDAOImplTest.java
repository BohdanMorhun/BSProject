package model.dao.impl;

import model.dao.connection.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminDAOImplTest {

    @Mock
    private ResultSet resultSet;
    @Mock
    private ConnectionFactory connectionFactory;
    private AdminDAOImpl adminDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        adminDAO = new AdminDAOImpl(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void parseToOneElement() throws SQLException {
        when(resultSet.getInt(any())).thenReturn(1);
        when(resultSet.getString(any())).thenReturn("test");
        assertEquals(new Integer(1), adminDAO.parseToOneElement(resultSet).getId());
        assertEquals("test", adminDAO.parseToOneElement(resultSet).getName());
        assertNotNull(adminDAO.parseToOneElement(resultSet));
    }

    @Test
    public void getAdminByUserId() {

    }

}