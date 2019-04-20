package controller.service.transaction;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import model.exception.DAOException;

import java.sql.Connection;

public interface TransactionUnit<E> {

    E execute(Connection connection) throws DAOException, ServiceLayerException;
}
