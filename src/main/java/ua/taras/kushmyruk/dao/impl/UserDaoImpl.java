package ua.taras.kushmyruk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.dao.ConnectionBuilder;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;

public class UserDaoImpl implements UserDao {
  private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
  private final String INSERT_USER = "INSERT INTO usr (username, password, email, is_active)"
      + "VALUES(?,?,?,?,?);";

  private final String INSERT_USER_ROLE = "INSERT INTO user_role (username, user_role) "
      + "VALUES(?,?);";

  private final String SELECT_USER_BY_USERNAME = "SELECT * FROM usr u WHERE u.username = ?; ";

  private final String SELECT_USER_ROLE = "SELECT * FROM user_role r WHERE r.username = ?";

  private Connection getConnection() throws SQLException {
    return ConnectionBuilder.getConnection();
  }

  @Override
  public User findUserByUsername(String username) {
    try (Connection connection = getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)){
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.getString("Username").equals(username)) {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(getUserRole(connection, username));
        return user;
      }

    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  private UserRole getUserRole (Connection connection, String username) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(SELECT_USER_ROLE);
    statement.setString(1, username);
    ResultSet resultSet = statement.executeQuery();
    UserRole userRole = UserRole.valueOf(resultSet.getString("user_role"));
    return userRole;
  }

  @Override
  public long saveUser(User user) throws DaoException {
    Long result = -1l;
    logger.debug("User - {}", user.getUsername());

    try(Connection connection = getConnection()) {

      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
      connection.setAutoCommit(false);
      try{
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setBoolean(4, user.isActive());
        saveUserRole(connection, user.getUsername());
        preparedStatement.executeUpdate();
      }catch (SQLException e){
        logger.error(e.getMessage());
        connection.rollback();
      }
    }catch (SQLException e){
      logger.error(e.getMessage());
    }
    return 1;
  }


  public void saveUserRole(Connection connection,String username) throws SQLException{
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_ROLE);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, UserRole.ADMIN.toString());
    preparedStatement.executeUpdate();
  }

}
