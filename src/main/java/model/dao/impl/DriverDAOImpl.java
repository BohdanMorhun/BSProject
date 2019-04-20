package model.dao.impl;

import domain.Bus;
import domain.Driver;
import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.DriverDAO;
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

import static model.dao.constants.DAOConstants.DriverDBColumns;
import static model.dao.constants.DAOConstants.UserDBColumns;

public class DriverDAOImpl extends AbstractGenericDAO<Driver> implements DriverDAO {

    private ResourceManager resourceManager;
    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(DriverDAOImpl.class);

    public DriverDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected Driver parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Driver.DriverBuilder()
                .setId(resultSet.getInt(DriverDBColumns.DRIVER_ID))
                .setName(resultSet.getString(DriverDBColumns.DRIVER_NAME))
                .setSurname(resultSet.getString(DriverDBColumns.DRIVER_SURNAME))
                .setBirth(resultSet.getDate(DriverDBColumns.DRIVER_BIRTH))
                .setLicenseTest(resultSet.getDate(DriverDBColumns.DRIVER_LICENSE_TEST))
                .setSalary(resultSet.getInt(DriverDBColumns.DRIVER_SALARY))
                .setStatus(resultSet.getString(DriverDBColumns.DRIVER_STATUS))
                .setBus(new Bus.BusBuilder()
                        .setId(resultSet.getInt(DriverDBColumns.DRIVER_ID_BUS))
                        .createBus())
                .setUser(new User.UserBuilder()
                        .setId(resultSet.getInt(UserDBColumns.USER_ID))
                        .setLogin(resultSet.getString(UserDBColumns.USER_LOGIN))
                        .setPassword(resultSet.getString(UserDBColumns.USER_PASSWORD))
                        .createUser())
                .createDriver();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setDate(4, element.getLicenseTest());
        statement.setDouble(5, element.getSalary());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public Driver getDriverByBusId(int idBus) throws DAOException {
        logger.info("Getting driver by bus id. idBus = " + idBus);
        try (Connection connection = connectionFactory.getConnection()) {
            return getElementByIdAndQuery(idBus, resourceManager.getQuery("sql.driver.get.by.bus.id"), connection);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_DRIVER_BY_BUS_ID, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_DRIVER_BY_BUS_ID, e);
        }
    }

    @Override
    public Driver getDriverByBusId(int idBus, Connection connection) throws DAOException {
        return getElementByIdAndQuery(idBus, resourceManager.getQuery("sql.driver.get.by.bus.id"), connection);
    }

    @Override
    public Driver getDriverByUserId(int idUser, Connection connection) throws DAOException {
        return getElementByIdAndQuery(idUser, resourceManager.getQuery("sql.driver.get.by.user.id"), connection);
    }

    @Override
    public void setStatusNew(int idDriver, Connection connection) throws DAOException {
        logger.info("Setting status new for driver");
        try(PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.driver.set.status"))){
            statement.setString(1, Constants.STATUS_NEW);
            statement.setInt(2, idDriver);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_SET_STATUS_NEW, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_SET_STATUS_NEW, e);
        }
        logger.info("Status accepted");
    }

    @Override
    public void setStatusWork(int idDriver) throws DAOException {
        logger.info("Setting status work for driver");
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.driver.set.status"))){
            statement.setString(1, Constants.STATUS_WORK);
            statement.setInt(2, idDriver);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_SET_STATUS_WORK, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_SET_STATUS_WORK, e);
        }
        logger.info("Status accepted");
    }

    @Override
    public void cancelDriverFromBus(int idBus, Connection connection) throws DAOException {
        logger.info("Canceling driver from bus");
        try (PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.driver.cancel.driver"))) {
            statement.setString(1, Constants.STATUS_WORK);
            statement.setInt(2, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_CANCEL_DRIVER_FROM_BUS, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_CANCEL_DRIVER_FROM_BUS, e);
        }
        logger.info("Driver canceled");
    }

    @Override
    public void updateBusInfoForDriver(int idBus, int idDriver, Connection connection) throws DAOException {
        logger.info("Updating bus info for driver. Assigning new bus.");
        try(PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.driver.assign.bus"))){
            statement.setInt(1, idBus);
            statement.setInt(2, idDriver);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_UPDATE_BUS_INFO_FOR_DRIVER, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_UPDATE_BUS_INFO_FOR_DRIVER, e);
        }
        logger.info("New bus assigned");
    }

    @Override
    public List<Driver> getFreeDrivers() throws DAOException {
        logger.info("Getting free drivers");
        ResultSet resultSet = null;
        List<Driver> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.driver.get.by.status"))) {
            statement.setString(1, Constants.STATUS_FREE);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_FREE_DRIVERS, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_FREE_DRIVERS, e);
        }
        logger.info("Returning list of free drivers");
        return list;
    }

    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.driver.update");
    }

    @Override
    public String getDeleteElementQuery() {
        return resourceManager.getQuery("sql.driver.delete");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.driver.get.by.id");
    }

    @Override
    public String getInsertElementQuery() {
        return resourceManager.getQuery("sql.driver.insert");
    }

    @Override
    public String getElementsAmountQuery() {
        return resourceManager.getQuery("sql.driver.get.elements.amount");
    }

    @Override
    public String getPaginatedListQuery() {
        return resourceManager.getQuery("sql.driver.get.paginated.list");
    }
}
