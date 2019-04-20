package controller.service.transaction;

import controller.exception.ServiceLayerException;
import model.exception.DAOException;

import java.sql.Connection;

public interface VoidTransactionUnit {

    void execute(Connection connection) throws DAOException, ServiceLayerException;
}
