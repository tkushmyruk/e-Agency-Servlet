package ua.taras.kushmyruk.service.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Parameters;

public class UserServiceImpl implements UserService {
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private static UserDao userDao = new UserDaoImpl();

  public UserServiceImpl() {
  }

  private static class UserServiceHolder {
    private static final UserServiceImpl instance = new UserServiceImpl();
  }

  public static UserServiceImpl getInstance() {
    return UserServiceHolder.instance;
  }

  @Override
  public void loginUser(ServletRequest request, ServletResponse response) {
    String username = request.getParameter(Parameters.USERNAME);
    String password = request.getParameter(Parameters.PASSWORD);
    User userFromDb = userDao.findUserByUsername(username);
    if(userFromDb != null && userFromDb.getPassword().equals(password)) {
      request.setAttribute(Parameters.USER_AUTH, userFromDb.getUsername());
      request.setAttribute(Parameters.ROLE, userFromDb.getRole().toString());
      logger.info("User {} successfully signed in", username);
    }
  }

  @Override
  public void registrateUser(ServletRequest request, ServletResponse response) {
   String username = request.getParameter(Parameters.USERNAME);
   String password = request.getParameter(Parameters.PASSWORD);
   String email = request.getParameter(Parameters.EMAIL);
   User user = new User();
   user.setUsername(username);
   user.setPassword(password);
   user.setEmail(email);
    try {
      userDao.saveUser(user);
    } catch (DaoException e) {
      logger.error(e.getMessage());
    }
  }
}
