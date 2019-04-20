package model.dao;

import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

/**
 * Generic DAO for handling input data from service layer and retrieving from DB
 * */

public interface GenericDAO<E> {

    Integer insertElement(E element) throws DAOException;

    Integer insertElement(E element, Connection connection) throws DAOException;

    E getElementById(int id) throws DAOException;

    E getElementById(int id, Connection connection) throws DAOException;

    void deleteElement(int id, Connection connection) throws DAOException;

    void updateElement(E element) throws DAOException;

    void updateElement(E element, Connection connection) throws DAOException;

    Integer getElementsAmount() throws DAOException;

    List<E> getPaginatedList(int startIdx, int amountElements) throws DAOException;
}
