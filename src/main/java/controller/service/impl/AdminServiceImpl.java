package controller.service.impl;

import controller.constants.LogMessages;
import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.AdminService;
import controller.service.UserService;
import domain.Admin;
import domain.User;
import model.dao.AdminDAO;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

public class AdminServiceImpl extends AbstractGenericService<Admin> implements AdminService {

    private AdminDAO adminDAO;
    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(AdminDAO adminDAO) {
        super(adminDAO);
        this.adminDAO = adminDAO;
    }

    @Override
    public Admin getAdminByUserId(int idUser) throws ServiceLayerException {
        logger.info(LogMessages.GETTING_ADMIN_BY_USER_ID);
        try {
            return adminDAO.getAdminByUserId(idUser);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_ADMIN_BY_USER_ID);
            throw new ServiceLayerException(LogMessages.CAN_NOT_GET_ADMIN_BY_USER_ID, e);
        }
    }
}
