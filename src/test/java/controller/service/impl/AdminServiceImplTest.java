package controller.service.impl;

import controller.exception.ServiceLayerException;
import domain.Admin;
import model.dao.impl.AdminDAOImpl;
import model.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    private AdminDAOImpl adminDAO;
    private AdminServiceImpl adminService;

    @Before
    public void setUp() throws Exception {
        adminDAO = mock(AdminDAOImpl.class);
        adminService = new AdminServiceImpl(adminDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAdminByUserId() throws DAOException, ServiceLayerException {
        when(adminDAO.getAdminByUserId(anyInt())).thenReturn(new Admin.AdminBuilder().setId(1).createAdmin());
        assertEquals(new Integer(1), adminService.getAdminByUserId(anyInt()).getId());
        verify(adminDAO, atLeast(1)).getAdminByUserId(anyInt());
    }

    @Test
    public void isElementValid() {
    }
}