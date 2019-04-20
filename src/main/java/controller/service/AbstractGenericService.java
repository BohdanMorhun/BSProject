package controller.service;

import controller.constants.LogMessages;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import model.dao.GenericDAO;
import model.exception.DAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.util.List;

/**
 * Abstract class which consists of general methods for connection to DB
 * See AbstractGenericDAO*/
public abstract class AbstractGenericService<E> implements GenericService<E>{

    private GenericDAO<E> genericDAO;
    private static final Logger logger = Logger.getLogger(AbstractGenericService.class);

    public AbstractGenericService(GenericDAO<E> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public Integer insertElement(E element) throws ServiceLayerException {
        logger.info(LogMessages.INSERTING_ELEMENT);
        try {
            return genericDAO.insertElement(element);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_INSERT_ELEMENT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_INSERT_ELEMENT, e);
        }
    }

    @Override
    public Integer insertElement(E element, Connection connection) throws ServiceLayerException {
        logger.info(LogMessages.INSERTING_ELEMENT);
        try {
            return genericDAO.insertElement(element, connection);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_INSERT_ELEMENT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_INSERT_ELEMENT, e);
        }
    }

    @Override
    public void updateElement(E element) throws ServiceLayerException {
        logger.info(LogMessages.UPDATING_ELEMENT);
        try {
            genericDAO.updateElement(element);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_UPDATE_ELEMENT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_UPDATE_ELEMENT, e);
        }
    }

    @Override
    public void updateElement(E element, Connection connection) throws ServiceLayerException {
        logger.info(LogMessages.UPDATING_ELEMENT);
        try {
            genericDAO.updateElement(element, connection);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_UPDATE_ELEMENT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_UPDATE_ELEMENT, e);
        }
    }

    @Override
    public E getElementById(int id) throws ServiceLayerException {
        logger.info(LogMessages.GETTING_ELEMENT_BY_ID);
        try {
            return genericDAO.getElementById(id);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_ELEMENT_BY_ID);
            throw new ServiceLayerException(LogMessages.CAN_NOT_GET_ELEMENT_BY_ID, e);
        }
    }

    @Override
    public E getElementById(int id, Connection connection) throws ServiceLayerException {
        logger.info(LogMessages.GETTING_ELEMENT_BY_ID);
        try {
            return genericDAO.getElementById(id, connection);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_ELEMENT_BY_ID);
            throw new ServiceLayerException(LogMessages.CAN_NOT_GET_ELEMENT_BY_ID, e);
        }
    }

    @Override
    public void deleteElement(int id, Connection connection) throws ServiceLayerException {
        logger.info(LogMessages.DELETING_ELEMENT);
        try {
            genericDAO.deleteElement(id, connection);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_DELETE_ELEMENT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_DELETE_ELEMENT, e);
        }
    }

    @Override
    public Integer getElementsAmount() throws ServiceLayerException {
        logger.info(LogMessages.GETTING_ELEMENTS_AMOUNT);
        try {
            return genericDAO.getElementsAmount();
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_ELEMENTS_AMOUNT);
            throw new ServiceLayerException(LogMessages.CAN_NOT_GET_ELEMENTS_AMOUNT, e);
        }
    }

    @Override
    public List<E> getPaginatedList(int startIdx, int endIdx) throws ServiceLayerException {
        logger.info(LogMessages.GETTING_PAGINATED_LIST);
        try {
            return genericDAO.getPaginatedList(startIdx, endIdx);
        } catch (DAOException e) {
            logger.error(LogMessages.CAN_NOT_GET_PAGINATED_LIST);
            throw new ServiceLayerException(e);
        }
    }
}
