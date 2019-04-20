package model.dao.impl;

import domain.Route;
import model.dao.AbstractGenericDAO;
import model.dao.RouteDAO;
import model.dao.connection.ConnectionFactory;
import model.dao.constants.Constants;
import model.dao.constants.ExceptionMessages;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static model.dao.constants.DAOConstants.RouteDBColumns;

public class RouteDAOImpl extends AbstractGenericDAO<Route> implements RouteDAO {

    private ResourceManager resourceManager;
    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(RouteDAOImpl.class);

    public RouteDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected Route parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Route.RouteBuilder()
                .setId(resultSet.getInt(RouteDBColumns.ROUTE_ID))
                .setNumber(resultSet.getString(RouteDBColumns.ROUTE_NUMBER))
                .setTitle(resultSet.getString(RouteDBColumns.ROUTE_TITLE))
                .setDistance(resultSet.getInt(RouteDBColumns.ROUTE_DISTANCE))
                .setStatus(resultSet.getString(RouteDBColumns.ROUTE_STATUS))
                .setDeparture(resultSet.getString(RouteDBColumns.ROUTE_DEPARTURE))
                .setArrival(resultSet.getString(RouteDBColumns.ROUTE_ARRIVAL))
                .createRoute();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Route element) throws SQLException {
        statement.setString(1, element.getNumber());
        statement.setString(2, element.getTitle());
        statement.setInt(3, element.getDistance());
        statement.setString(4, element.getDeparture());
        statement.setString(5, element.getArrival());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Route element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(6, element.getId());
    }

    @Override
    public void setStatusEmpty(int idRoute, Connection connection) throws DAOException {
        setStatus(idRoute, connection, Constants.STATUS_EMPTY);
    }

    @Override
    public void setStatusWork(int idRoute) throws DAOException {
        try (Connection connection = connectionFactory.getConnection()){
            setStatus(idRoute, connection, Constants.STATUS_WORK);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_SET_STATUS + Constants.STATUS_WORK, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_SET_STATUS + Constants.STATUS_WORK, e);
        }
    }

    @Override
    public void setStatusWork(int idRoute, Connection connection) throws DAOException {
        setStatus(idRoute, connection, Constants.STATUS_WORK);
    }

    private void setStatus(int idRoute, Connection connection, String status) throws DAOException {
        logger.info("Setting status " + status + " for route");
        try (PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.route.update.status"))){
            statement.setString(1,status);
            statement.setInt(2, idRoute);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_SET_STATUS + status, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_SET_STATUS + status, e);
        }
    }

    @Override
    public void cancelAll(int idRoute, Connection connection) throws DAOException {
        logger.info("Canceling all buses which assigned to route");
        try(PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.route.cancel.all"))){
            statement.setInt(1, idRoute);
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error(ExceptionMessages.CAN_NOT_CANCEL_ALL_BUSES_FROM_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_CANCEL_ALL_BUSES_FROM_ROUTE, e);
        }
        logger.info("All buses canceled");
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) throws DAOException {
        logger.info("Searching by criteria");
        ResultSet resultSet = null;
        List<Route> list;
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.route.get.by.criteria"))) {
            statement.setString(1, departure + Constants.LIKE);
            statement.setString(2, arrival + Constants.LIKE);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_FOUND_ROUTES_BY_CRITERIA, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_FOUND_ROUTES_BY_CRITERIA, e);
        }
        logger.info("Returning list of routes according to criteria");
        return list;
    }


    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.route.update");
    }

    @Override
    public String getDeleteElementQuery() {
        return resourceManager.getQuery("sql.route.delete");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.route.get.by.id");
    }

    @Override
    public String getInsertElementQuery() {
        return resourceManager.getQuery("sql.route.insert");
    }

    @Override
    public String getElementsAmountQuery() {
        return resourceManager.getQuery("sql.route.get.elements.amount");
    }

    @Override
    public String getPaginatedListQuery() {
        return resourceManager.getQuery("sql.route.get.paginated.list");
    }
}
