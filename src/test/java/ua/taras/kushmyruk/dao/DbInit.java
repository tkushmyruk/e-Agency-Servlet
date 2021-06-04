package ua.taras.kushmyruk.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DbInit {
  public static void startup() throws URISyntaxException, IOException {
    URL url = UserDaoTest.class.getClassLoader().getResource("sql/create_tables.sql");
    List<String> lines =  Files.readAllLines(Paths.get(url.toURI()));
    String sql =  lines.stream().collect(Collectors.joining());
    URL url1 = UserDaoTest.class.getClassLoader().getResource("sql/insert_data.sql");
    List<String> linesWithMockData = Files.readAllLines(Paths.get(url1.toURI()));
    String sqlWithMockData =  linesWithMockData.stream().collect(Collectors.joining());
    try (Connection connection = ConnectionBuilder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        PreparedStatement dataStatement = connection.prepareStatement(sqlWithMockData)){
      statement.executeUpdate();
      dataStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
