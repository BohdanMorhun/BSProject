package model.dao.impl;

import domain.Schedule;
import model.dao.AbstractGenericDAO;
import model.dao.ScheduleDAO;
import model.dao.connection.ConnectionFactory;
import resource_manager.ResourceManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static model.dao.constants.DAOConstants.ScheduleDBColumns;

public class ScheduleDAOImpl extends AbstractGenericDAO<Schedule> implements ScheduleDAO {

    private ResourceManager resourceManager;

    public ScheduleDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        resourceManager = ResourceManager.INSTANCE;
    }

    @Override
    protected Schedule parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Schedule.ScheduleBuilder()
                .setId(resultSet.getInt(ScheduleDBColumns.SCHEDULE_ID))
                .setDeparture(resultSet.getString(ScheduleDBColumns.SCHEDULE_DEPARTURE))
                .setArrival(resultSet.getString(ScheduleDBColumns.SCHEDULE_ARRIVAL))
                .createSchedule();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Schedule element) throws SQLException {
        statement.setString(1, element.getDeparture());
        statement.setString(2, element.getArrival());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Schedule element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(3, element.getId());
    }

    @Override
    public String getUpdateElementQuery() {
        return resourceManager.getQuery("sql.schedule.update");
    }

    @Override
    public String getElementByIdQuery() {
        return resourceManager.getQuery("sql.schedule.get.by.id");
    }

    @Override
    public String getInsertElementQuery() {
        return resourceManager.getQuery("sql.schedule.insert");
    }
}
