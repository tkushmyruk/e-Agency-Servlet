package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;

public class RegistrationCommand implements Command {
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
    return null;
  }
}
