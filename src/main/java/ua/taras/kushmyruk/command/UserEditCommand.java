package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.util.Pages;

public class UserEditCommand implements Command {
  private final ProfileService profileService;

  public UserEditCommand(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    profileService.userEdit(request, response);
    profileService.getUserList(request, response);
    return Pages.USER_LIST_PAGE;
  }
}
