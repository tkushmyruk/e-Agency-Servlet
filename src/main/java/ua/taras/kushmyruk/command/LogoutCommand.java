package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.LoggerMessage;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class LogoutCommand implements Command {
  private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);
  private final UserService userService;

  public LogoutCommand(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
    System.out.println("LOGOOUT STARTUYEM");
    LOGGER.info(LoggerMessage.USER_LOGOUT);
    System.out.println("Logout method");
    userService.logoutUser(request);
    return Pages.INDEX_PAGE;
  }

}
