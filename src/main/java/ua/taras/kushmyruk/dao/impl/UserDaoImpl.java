package ua.taras.kushmyruk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
      + "VALUES(?,?,?,?);";

  private final String INSERT_USER_ROLE = "INSERT INTO user_role (username, role) "
      + "VALUES(?,?);";

  private final String GET_USER_BY_USERNAME = "SELECT * FROM usr u WHERE u.username = ?; ";

  private final String GET_USER_ROLE = "SELECT * FROM user_role r WHERE r.username = ?";

  private final String GET_ALL_USERS = "SELECT * FROM usr u INNER JOIN user_role r "
      + "ON u.username = r.username;";

  private final String UPDATE_IS_ACTIVE = "UPDATE usr SET is_active = ? WHERE username = ?;";

  private final String UPDATE_USER_ROLE = "UPDATE user_role SET role = ? WHERE username = ?;";

  private Connection getConnection() throws SQLException {
    Connection connection = ConnectionBuilder.getConnection();
    return connection;
  }

  @Override
  public User findUserByUsername(String username) {
    try (Connection connection = getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME)){
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getString("username").equals(username)) {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(getUserRole(connection, username));
        return user;
      }

    } catch (SQLException e) {
      logger.error(e.getMessage());
      System.out.println(e.getMessage());
    }
    return null;
  }

  private UserRole getUserRole (Connection connection, String username) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_USER_ROLE);
    statement.setString(1, username);
    ResultSet resultSet = statement.executeQuery();
    if(resultSet.next()) {
      UserRole userRole = UserRole.valueOf(resultSet.getString("role"));
      return userRole;
    }
    return null;
  }

  @Override
  public void setActive(String username) {
    try(Connection connection = getConnection()) {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(UPDATE_IS_ACTIVE);
      try {
        System.out.println("set active!");
        statement.setBoolean(1, true);
        statement.setString(2, username);
        statement.executeUpdate();
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setBlock(String username) {
    try(Connection connection = getConnection()) {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(UPDATE_IS_ACTIVE);
      try {
        System.out.println("set block!");
        statement.setBoolean(1, false);
        statement.setString(2, username);
        statement.executeUpdate();
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void changeUserRole(String username, String userRole) {
    try(Connection connection = getConnection()) {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE);
      try {
        System.out.println("change role!");
        System.out.println(username + " " + username);
        statement.setString(1, userRole);
        statement.setString(2, username);
        int i = statement.executeUpdate();
        System.out.println(i);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> findAllUsers() {
    List<User> list = new ArrayList<>();
    try(Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()){
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setActive(resultSet.getBoolean("is_active"));
        user.setRole(UserRole.valueOf(resultSet.getString("role")));
        list.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public long saveUser(User user) throws DaoException {
    Long result = -1l;
    logger.debug("User - {}", user.getUsername());

    try(Connection connection = getConnection()) {

      PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, new String[]{"id"});
      connection.setAutoCommit(false);
      try{
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setBoolean(4, user.isActive());
        preparedStatement.executeUpdate();
        saveUserRole(connection, user.getUsername());
        connection.commit();
      }catch (SQLException e){
        logger.error(e.getMessage());
        connection.rollback();
      }
    }catch (SQLException e){
      logger.error(e.getMessage());
    }
    return result;
  }


  public int saveUserRole(Connection connection,String username) throws SQLException{
    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_ROLE);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, UserRole.ADMIN.toString());
    int i = preparedStatement.executeUpdate();
    return i;
  }

}
