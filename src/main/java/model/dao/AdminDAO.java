package model.dao;

import domain.Admin;
import model.exception.DAOException;

public interface AdminDAO extends GenericDAO<Admin>{

    Admin getAdminByUserId(int idUser) throws DAOException;
}
