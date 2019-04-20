package model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    Connection getConnection() throws SQLException;

    void freeConnection(Connection connection);

    void rollBack(Connection connection);
}
