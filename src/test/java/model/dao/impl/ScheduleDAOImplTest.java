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

public class ScheduleDAOImplTest {

    @Mock
    private ResultSet resultSet;
    @Mock
    private ConnectionFactory connectionFactory;

    private ScheduleDAOImpl scheduleDAO;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        scheduleDAO = new ScheduleDAOImpl(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseToOneElement() throws SQLException {
        when(resultSet.getInt(any())).thenReturn(3);
        assertEquals(new Integer(3), scheduleDAO.parseToOneElement(resultSet).getId());
        assertNotNull(scheduleDAO.parseToOneElement(resultSet));
    }
}