package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.util.Pages;

public class BoughtTourListCommand implements Command {
  private final ProfileService profileService;

  public BoughtTourListCommand(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    profileService.getUserList(request, response);
    return Pages.BOUGHT_TOURS_PAGE;
  }
}
