package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;

public class LoginCommand  implements Command{
  private UserService userService;

  public LoginCommand(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    userService.loginUser(request, response);
    return Pages.INDEX_PAGE;
  }

  @Override
  public String doOnError(HttpServletRequest request, Exception e) throws AppException {
    return Pages.ERROR_PAGE;
  }
}
