package controller.service.impl;

import controller.exception.ServiceLayerException;
import domain.User;
import model.dao.impl.UserDAOImpl;
import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserDAOImpl userDAO;

    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userDAO = mock(UserDAOImpl.class);
        userService = new UserServiceImpl(userDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserByLoginData() throws DAOException, ServiceLayerException {
        when(userDAO.findUserByLoginData(any())).thenReturn(new User.UserBuilder().setLogin("login").createUser());
        assertEquals("login", userService.findUserByLoginData(any()).getLogin());
        assertNotNull(userService.findUserByLoginData(any()));
        verify(userDAO, atLeast(1)).findUserByLoginData(any());
    }

    @Test
    public void isElementValid() {
    }
}