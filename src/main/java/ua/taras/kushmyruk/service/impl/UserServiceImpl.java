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
import ua.taras.kushmyruk.validator.LoginValidator;
import ua.taras.kushmyruk.validator.RegistrationValidator;

public class UserServiceImpl implements UserService {
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private static LoginValidator loginValidator = new LoginValidator();
  private static RegistrationValidator registrationValidator = new RegistrationValidator();
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
    loginValidator.validateEmptyCredentials(username, password);
    User userFromDb = userDao.findUserByUsername(username);
    loginValidator.validateUser(userFromDb, password);
    if(userFromDb != null && userFromDb.getPassword().equals(password) && userFromDb.isActive()){
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
   registrationValidator.validateCredentrials(username, password, email);
   User userFromDb = userDao.findUserByUsername(username);
   registrationValidator.validateIfUserExists(userFromDb);
   User user = new User();
   user.setUsername(username);
   user.setPassword(password);
   user.setEmail(email);
   user.setActive(true);
   user.setRole(UserRole.USER);
    System.out.println(user.getRole().toString());
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
    request.getSession().invalidate();
  }

  @Override
  public void changePassword(HttpServletRequest request, HttpServletResponse response) {
  String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
  String password = request.getParameter(Parameters.PASSWORD);
  String email = request.getParameter(Parameters.EMAIL);
  registrationValidator.validateCredentrials(username, password, email);
  userDao.updateUser(username, password, email);
  }

  @Override
  public void getUser(HttpServletRequest request, HttpServletResponse response) {
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    User user = userDao.findUserByUsername(username);
    request.setAttribute(Parameters.USER, user);
  }

  @Override
  public void sendMessage(HttpServletRequest request, HttpServletResponse response) {
   String topic = request.getParameter(Parameters.TOPIC);
   String tag = request.getParameter(Parameters.TAG);
   String text = request.getParameter(Parameters.MESSAGE_TEXT);
   String username = request.getParameter(Parameters.RECEIVER);
   userDao.saveMessage(topic, tag, text, username);
  }

  @Override
  public void getUserMessages(HttpServletRequest request, HttpServletResponse response) {
    request.setAttribute(Parameters.USER_MESSAGE, userDao.getUserMessages(
        (String) request.getSession().getAttribute(Parameters.USER)) );
  }
}
