package model.dao;

import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbstractGenericDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private AbstractGenericDAO abstractGenericDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertElement() throws DAOException {

    }

    @Test
    public void getElementById() {
    }

    @Test
    public void getElementById1() {
    }

    @Test
    public void getElementsAmount() {
    }

    @Test
    public void getPaginatedList() {
    }

    @Test
    public void getElementByIdAndQuery() throws DAOException {
        when(abstractGenericDAO.getElementByIdAndQuery(1, "select", connection)).thenReturn(new Object());
        assertNotNull(abstractGenericDAO.getElementByIdAndQuery(1, "select", connection));
        verify(abstractGenericDAO, atLeast(1)).getElementByIdAndQuery(1, "select", connection);
    }

    @Test
    public void parseAllElements() {
    }

    @Test
    public void parseToOneElement() {
    }
}