package model.dao.impl;

import domain.Bus;
import domain.Route;
import domain.Schedule;
import model.dao.AbstractGenericDAO;
import model.dao.BusDAO;
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

import static model.dao.constants.DAOConstants.BusDBColumns;
import static model.dao.constants.DAOConstants.ScheduleDBColumns;

public class BusDAOImpl extends AbstractGenericDAO<Bus> implements BusDAO {

    private ConnectionFactory connectionFactory;
    private ResourceManager resourceManager;
    private static final Logger logger = Logger.getLogger(BusDAOImpl.class);

    public BusDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected Bus parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Bus.BusBuilder()
                .setId(resultSet.getInt(BusDBColumns.BUS_ID))
                .setPlate(resultSet.getString(BusDBColumns.BUS_PLATE))
                .setModel(resultSet.getString(BusDBColumns.BUS_MODEL))
                .setMileage(resultSet.getInt(BusDBColumns.BUS_MILEAGE))
                .setInspection(resultSet.getDate(BusDBColumns.BUS_INSPECTION))
                .setConsumption(resultSet.getInt(BusDBColumns.BUS_CONSUMPTION))
                .setRelease(resultSet.getDate(BusDBColumns.BUS_RELEASE_DATE))
                .setSeats(resultSet.getInt(BusDBColumns.BUS_SEATS))
                .setStatus(resultSet.getString(BusDBColumns.BUS_STATUS))
                .setRoute(new Route.RouteBuilder()
                        .setId(resultSet.getInt(BusDBColumns.BUS_ID_ROUTE))
                        .createRoute())
                .setSchedule(new Schedule.ScheduleBuilder()
                        .setId(resultSet.getInt(ScheduleDBColumns.SCHEDULE_ID))
                        .setDeparture(resultSet.getString(ScheduleDBColumns.SCHEDULE_DEPARTURE))
                        .setArrival(resultSet.getString(ScheduleDBColumns.SCHEDULE_ARRIVAL))
                        .createSchedule())
                .createBus();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Bus element) throws SQLException {
        statement.setString(1, element.getPlate());
        statement.setString(2, element.getModel());
        statement.setInt(3, element.getMileage());
        statement.setDate(4, element.getInspection());
        statement.setInt(5, element.getConsumption());
        statement.setDate(6, element.getRelease());
        statement.setInt(7, element.getSeats());
        statement.setInt(8, element.getSchedule().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Bus element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(9, element.getId());
    }

    @Override
    public Integer countBusesOnRouteById(int idRoute, Connection connection) throws DAOException {
        logger.info("Counting buses assigned to route");
        ResultSet resultSet = null;
        Integer res = null;
        try (PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.bus.get.elements.amount.by.idroute"))) {
            statement.setInt(1, idRoute);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt(Constants.ELEMENTS_AMOUNT);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_COUNT_BUSES_BY_ID_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_COUNT_BUSES_BY_ID_ROUTE, e);
        }
        logger.info("Quantity counted buses is " + res);
        return res;
    }

    @Override
    public List<Bus> getBusesByIdRoute(int idRoute) throws DAOException {
        logger.info("Getting buses assigned to route");
        ResultSet resultSet = null;
        List<Bus> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.bus.get.buses.by.route"))) {
            statement.setInt(1, idRoute);
            resultSet = statement.executeQuery();
            list =  parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_BUSES_BY_ID_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_BUSES_BY_ID_ROUTE, e);
        }
        logger.info("Returning list buses assigned to route");
        return list;
    }

    @Override
    public List<Bus> getFreeBuses() throws DAOException {
        logger.info("Getting free buses");
        ResultSet resultSet = null;
        List<Bus> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.bus.get.by.status"))) {
            statement.setString(1, Constants.STATUS_FREE);
            resultSet = statement.executeQuery();
            list =  parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_FREE_BUSES, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_FREE_BUSES, e);
        }
        logger.info("Returning list of free buses");
        return list;
    }

    @Override
    public void cancelBusFromRoute(int idBus, Connection connection) throws DAOException {
        logger.info("Canceling bus from route");
        try (PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.bus.cancel.bus"))) {
            statement.setString(1, Constants.STATUS_FREE);
            statement.setString(2, Constants.STATUS_FREE);
            statement.setInt(3, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_CANCEL_BUS_FROM_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_CANCEL_BUS_FROM_ROUTE, e);
        }
        logger.info("Bus canceled");
    }

    @Override
    public void appointBusToRoute(int idRoute, int idBus, Connection connection) throws DAOException {
        logger.info("Assigning bus to route");
        try (PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.bus.appoint.to.route"))) {
            statement.setString(1, Constants.STATUS_WORK);
            statement.setString(2, Constants.STATUS_WORK);
            statement.setInt(3, idRoute);
            statement.setInt(4, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_APPOINT_BUS_TO_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_APPOINT_BUS_TO_ROUTE, e);
        }
        logger.info("Bus assigned to route");
    }

    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.bus.update");
    }

    @Override
    public String getDeleteElementQuery() {
        return resourceManager.getQuery("sql.bus.delete");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.bus.get.by.id");
    }

    @Override
    public String getInsertElementQuery() {
        return resourceManager.getQuery("sql.bus.insert");
    }

    @Override
    public String getElementsAmountQuery() {
        return resourceManager.getQuery("sql.bus.get.elements.amount");
    }

    @Override
    public String getPaginatedListQuery() {
        return resourceManager.getQuery("sql.bus.get.paginated.list");
    }
}
