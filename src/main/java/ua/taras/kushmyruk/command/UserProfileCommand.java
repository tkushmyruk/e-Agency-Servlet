package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;

public class UserProfileCommand implements Command {
  private final UserService userService;

  public UserProfileCommand(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    userService.getUser(request, response);
    return Pages.USER_PROFILE_PAGE;
  }
}
