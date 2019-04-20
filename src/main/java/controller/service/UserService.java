package controller.service;

import controller.exception.ServiceLayerException;
import domain.User;

public interface UserService extends GenericService<User> {

    User findUserByLoginData(User user) throws ServiceLayerException;

}
