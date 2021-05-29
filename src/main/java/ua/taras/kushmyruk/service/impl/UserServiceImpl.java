package ua.taras.kushmyruk.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;
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
  public void loginUser(HttpServletRequest request, HttpServletResponse response) {
    String username = request.getParameter(Parameters.USERNAME);
    String password = request.getParameter(Parameters.PASSWORD);
    User userFromDb = userDao.findUserByUsername(username);
    if(userFromDb != null && userFromDb.getPassword().equals(password)) {
      request.getSession().setAttribute(Parameters.USER_AUTH, userFromDb.getUsername());
      request.getSession().setAttribute(Parameters.ROLE, userFromDb.getRole().toString());
      logger.info("User {} successfully signed in", username);
    }
  }

  @Override
  public void registrateUser(HttpServletRequest request, HttpServletResponse response) {
   String username = request.getParameter(Parameters.USERNAME);
   String password = request.getParameter(Parameters.PASSWORD);
   String email = request.getParameter(Parameters.EMAIL);
   User user = new User();
   user.setUsername(username);
   user.setPassword(password);
   user.setEmail(email);
   user.setActive(true);
   user.setRole(UserRole.ADMIN);
    try {
      userDao.saveUser(user);
    } catch (DaoException e) {
      logger.error(e.getMessage());
    }
    request.getSession().setAttribute(Parameters.USER_AUTH, user.getUsername());
    request.getSession().setAttribute(Parameters.USER, user);
  }

  @Override
  public void logoutUser(HttpServletRequest request) {
    System.out.println(request.getSession().getAttribute(Parameters.USER_AUTH));
    System.out.println("Auth");
    request.getSession().invalidate();
    System.out.println("After invalidate");
  }
}
