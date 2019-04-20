package controller.service;

import controller.exception.ServiceLayerException;
import domain.Admin;

public interface AdminService extends GenericService<Admin>{

    Admin getAdminByUserId(int idUser) throws ServiceLayerException;
}
