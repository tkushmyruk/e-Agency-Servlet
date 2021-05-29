package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.util.Pages;

public class UserListCommand implements Command {
  private final ProfileService profileService;

  public UserListCommand(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    profileService.getUserList(request, response);
    return Pages.USER_LIST_PAGE;
  }

  @Override
  public String doOnError(HttpServletRequest request, Exception e) throws AppException {
    return null;
  }
}
