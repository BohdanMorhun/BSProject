package model.dao.connection.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConnectionFactoryImplTest {

    @Mock
    private ConnectionFactoryImpl connectionFactory;
    @Mock
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void getConnection() throws SQLException {
        when(connectionFactory.getConnection()).thenReturn(connection);
        assertNotNull(connectionFactory.getConnection());
        verify(connectionFactory, atLeast(1)).getConnection();
    }
}
