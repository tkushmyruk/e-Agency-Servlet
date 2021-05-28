package ua.taras.kushmyruk.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

public class ConnectionBuilderTest {

  @Test
  public void test() throws SQLException, DaoException {
    UserDao userDao = new UserDaoImpl();
    User mm = userDao.findUserByUsername("mm");
    Assert.assertNotNull(mm);

  }

}