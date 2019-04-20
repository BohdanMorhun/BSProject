package model.dao.impl;

import domain.Admin;
import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.AdminDAO;
import model.dao.connection.ConnectionFactory;
import model.dao.constants.ExceptionMessages;
import model.exception.DAOException;
import resource_manager.ResourceManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static model.dao.constants.DAOConstants.*;

public class AdminDAOImpl extends AbstractGenericDAO<Admin> implements AdminDAO {

    private ResourceManager resourceManager;
    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

    public AdminDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected Admin parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Admin.AdminBuilder()
                .setId(resultSet.getInt(AdminDBColumns.ADMIN_ID))
                .setName(resultSet.getString(AdminDBColumns.ADMIN_NAME))
                .setSurname(resultSet.getString(AdminDBColumns.ADMIN_SURNAME))
                .setBirth(resultSet.getDate(AdminDBColumns.ADMIN_BIRTH))
                .setDegree(resultSet.getString(AdminDBColumns.ADMIN_DEGREE))
                .setGraduation(resultSet.getDate(AdminDBColumns.ADMIN_GRADUATION))
                .setUser(new User.UserBuilder()
                        .setId(resultSet.getInt(UserDBColumns.USER_ID))
                        .setLogin(resultSet.getString(UserDBColumns.USER_LOGIN))
                        .setPassword(resultSet.getString(UserDBColumns.USER_PASSWORD))
                        .createUser())
                .createAdmin();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setString(4, element.getDegree());
        statement.setDate(5, element.getGraduation());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public Admin getAdminByUserId(int idUser) throws DAOException {
        logger.info("Getting admin by user id");
        try (Connection connection = connectionFactory.getConnection()) {
            return getElementByIdAndQuery(idUser, resourceManager.getQuery("sql.admin.get.by.user.id"), connection);
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_ADMIN_DATA_BY_USER_ID, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_GET_ADMIN_DATA_BY_USER_ID, e);
        }
    }

    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.admin.update");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.admin.insert");
    }
}
