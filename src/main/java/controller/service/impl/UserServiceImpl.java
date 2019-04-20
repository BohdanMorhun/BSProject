package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.UserService;
import domain.User;
import model.dao.UserDAO;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

public class UserServiceImpl extends AbstractGenericService<User> implements UserService {

    private UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }

    @Override
    public User findUserByLoginData(User user) throws ServiceLayerException {
        logger.info("Try to find user by login data");
        try {
            return userDAO.findUserByLoginData(user);
        } catch (DAOException e) {
            logger.error("Couldn't find user");
            throw new ServiceLayerException("Couldn't find user", e);
        }
    }
}
