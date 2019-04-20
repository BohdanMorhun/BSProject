package controller.service.transaction.impl;

import controller.exception.ServiceLayerException;
import controller.service.transaction.TransactionManager;
import controller.service.transaction.TransactionUnit;
import controller.service.transaction.VoidTransactionUnit;
import model.dao.connection.ConnectionFactory;
import model.dao.connection.impl.ConnectionFactoryImpl;
import model.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl<E> implements TransactionManager<E> {

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(TransactionManagerImpl.class);

    public TransactionManagerImpl() {
        connectionFactory = ConnectionFactoryImpl.getInstance();
    }

    @Override
    public E executeTransaction(TransactionUnit<E> unit) throws ServiceLayerException {
        logger.info("Executing transaction");
        E element = null;
        Connection connection = null;
        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            element = unit.execute(connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | DAOException e) {
            logger.error("Transaction failed");
            connectionFactory.rollBack(connection);
            throw new ServiceLayerException("Transaction failed", e);
        }finally {
            connectionFactory.freeConnection(connection);
        }
        logger.info("Transaction finished successful");
        return element;
    }

    @Override
    public void processVoidTransaction(VoidTransactionUnit unit) throws ServiceLayerException {
        logger.info("Executing transaction");
        Connection connection = null;
        try {
            connection = connectionFactory.getConnection();
            connection.setAutoCommit(false);
            unit.execute(connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | DAOException e) {
            logger.error("Transaction failed");
            connectionFactory.rollBack(connection);
            throw new ServiceLayerException("Transaction failed", e);
        } finally {
            connectionFactory.freeConnection(connection);
        }
        logger.info("Transaction finished successful");
    }
}
