package model.dao.impl;

import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.UserDAO;
import model.dao.connection.ConnectionFactory;
import model.dao.constants.ExceptionMessages;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.dao.constants.DAOConstants.UserDBColumns;

public class UserDAOImpl extends AbstractGenericDAO<User> implements UserDAO {

    private ResourceManager resourceManager;
    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected User parseToOneElement(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .setId(resultSet.getInt(UserDBColumns.USER_ID))
                .setLogin(resultSet.getString(UserDBColumns.USER_LOGIN))
                .setPassword(resultSet.getString(UserDBColumns.USER_PASSWORD))
                .setRole(resultSet.getString(UserDBColumns.USER_ROLE))
                .createUser();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, User element) throws SQLException {
        statement.setString(1, element.getLogin());
        statement.setString(2, element.getPassword());
        statement.setString(3, element.getRole());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, User element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(4, element.getId());
    }

    @Override
    public User findUserByLoginData(User user) throws DAOException {
        logger.info("Searching user by login data");
        ResultSet resultSet = null;
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(resourceManager.getQuery("sql.user.get.by.login.data"))){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                logger.info("Returning user from database");
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_FIND_USER_BY_LOGIN_DATA, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_FIND_USER_BY_LOGIN_DATA, e);
        }
        return null;
    }

    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.user.update");
    }

    @Override
    public String getDeleteElementQuery() {
        return resourceManager.getQuery("sql.user.delete");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.user.get.by.id");
    }

    @Override
    public String getInsertElementQuery() {
        return resourceManager.getQuery("sql.user.insert");
    }
}
