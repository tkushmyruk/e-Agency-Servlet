package ua.taras.kushmyruk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ua.taras.kushmyruk.config.DaoConfig;

public class ConnectionBuilder {
  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5433/ser_agency",
        "roman",
        "kalipso231094");
    return connection;
  }
}
