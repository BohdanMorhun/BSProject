package model.dao;

import domain.User;
import model.exception.DAOException;

public interface UserDAO extends GenericDAO<User>{

    User findUserByLoginData(User user) throws DAOException;
}
