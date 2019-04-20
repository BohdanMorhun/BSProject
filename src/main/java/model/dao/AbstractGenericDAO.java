package model.dao;

import model.dao.connection.ConnectionFactory;
import model.dao.constants.Constants;
import model.dao.constants.ExceptionMessages;
import model.dao.util.Queries;
import model.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * General methods for base operations such as insert, update, delete, create
 * */
public abstract class AbstractGenericDAO<E> implements GenericDAO<E>, Queries {

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(AbstractGenericDAO.class);

    public AbstractGenericDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Integer insertElement(E element) throws DAOException {
        try (Connection connection = connectionFactory.getConnection()) {
            return insert(element, connection);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_INSERT_ELEMENT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_INSERT_ELEMENT, e);
        }
    }

    @Override
    public Integer insertElement(E element, Connection connection) throws DAOException {
        return insert(element, connection);
    }

    private Integer insert(E element, Connection connection) throws DAOException {
        logger.info("Inserting element");
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(getInsertElementQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            setInsertElementProperties(statement, element);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                logger.info("Element created. Returning it to service layer");
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_INSERT_ELEMENT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_INSERT_ELEMENT, e);
        }
        return null;
    }

    @Override
    public void updateElement(E element) throws DAOException {
        try (Connection connection = connectionFactory.getConnection()) {
            update(element, connection);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_UPDATE_ELEMENT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_UPDATE_ELEMENT, e);
        }
    }

    @Override
    public void updateElement(E element, Connection connection) throws DAOException {
        update(element, connection);
    }

    private void update(E element, Connection connection) throws DAOException {
        logger.info("Updating element");
        try (PreparedStatement statement = connection.prepareStatement(getUpdateElementQuery())) {
            setUpdateElementProperties(statement, element);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_UPDATE_ELEMENT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_UPDATE_ELEMENT, e);
        }
        logger.info("Element updated");
    }

    @Override
    public E getElementById(int id) throws DAOException {
        try (Connection connection = connectionFactory.getConnection()) {
            return getElementByIdAndQuery(id, getElementByIdQuery(), connection);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_ELEMENT_BY_ID, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_ELEMENT_BY_ID, e);
        }
    }

    @Override
    public E getElementById(int id, Connection connection) throws DAOException {
        return getElementByIdAndQuery(id, getElementByIdQuery(), connection);
    }

    @Override
    public void deleteElement(int id, Connection connection) throws DAOException {
        logger.info("Deleting element");
        try (PreparedStatement statement = connection.prepareStatement(getDeleteElementQuery())) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_DELETE_ELEMENT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_DELETE_ELEMENT, e);
        }
        logger.info("Element was deleted from database");
    }

    @Override
    public Integer getElementsAmount() throws DAOException {
        logger.info("Getting amount of element");
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(getElementsAmountQuery());
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                logger.info("Returning amount of entity");
                return resultSet.getInt(Constants.ELEMENTS_AMOUNT);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_ELEMENTS_AMOUNT, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_ELEMENTS_AMOUNT, e);
        }
        return null;
    }

    @Override
    public List<E> getPaginatedList(int startIdx, int amountElements) throws DAOException {
        logger.info("Getting paginated list");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(getPaginatedListQuery())) {
            statement.setInt(1, startIdx);
            statement.setInt(2, amountElements);
            resultSet = statement.executeQuery();
            list =  parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_PAGINATED_LIST, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_PAGINATED_LIST, e);
        }
        logger.info("Returning paginated list");
        return list;
    }

    protected E getElementByIdAndQuery(int id, String query, Connection connection) throws DAOException {
        logger.info("Getting element by id and query.");
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_BY_ID_AND_QUERY, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_BY_ID_AND_QUERY, e);
        }
        return null;
    }

    protected List<E> parseAllElements(ResultSet resultSet) throws SQLException {
        List<E> elements = new ArrayList<>();
        while (resultSet.next()) {
            elements.add(parseToOneElement(resultSet));
        }
        return elements;
    }

    protected abstract void setInsertElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract void setUpdateElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract E parseToOneElement(ResultSet resultSet) throws SQLException;
}
