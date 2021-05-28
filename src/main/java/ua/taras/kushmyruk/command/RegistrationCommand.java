package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class RegistrationCommand implements Command {
  private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
  private UserService userService;

  public RegistrationCommand(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    userService.registrateUser(request, response);
    return Pages.LOGIN_PAGE;
  }

  @Override
  public String doOnError(HttpServletRequest request, Exception e) throws AppException {
    LOGGER.error(e.getMessage());
    request.setAttribute(Parameters.EXCEPTION, e.getMessage());
    return Pages.REGISTRATION_PAGE;
  }
}
