package controller.service;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;

import java.sql.Connection;
import java.util.List;

/**Generic Service for handling input data from view part and retrieving from DB
 * All overloaded methods with Connection input parameter relates to transactions
 * */
public interface GenericService<E> {

    /**Inserting element into DB
     * @return id column*/

    Integer insertElement(E element) throws ServiceLayerException;

    Integer insertElement(E element, Connection connection) throws ServiceLayerException;

    E getElementById(int id) throws ServiceLayerException;

    E getElementById(int id, Connection connection) throws ServiceLayerException;

    void deleteElement(int id, Connection connection) throws ServiceLayerException;

    void updateElement(E element) throws WrongInputDataException, ServiceLayerException;

    void updateElement(E element, Connection connection) throws ServiceLayerException;

    /**
     * Count amount elements in appropriate table according to request
     * @return amount of elements int table*/
    Integer getElementsAmount() throws ServiceLayerException;

    /**
     * @return paginated list.
     * Actually it returns maximum 5 elements(it's hard coded value)*/
    List<E> getPaginatedList(int startIdx, int endIdx) throws ServiceLayerException;
}
