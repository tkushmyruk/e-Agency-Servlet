package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;

public class AddCreditCardCommand implements Command {
  private final ProfileService profileService;
  private final UserService userService;

  public AddCreditCardCommand(ProfileService profileService,
      UserService userService) {
    this.profileService = profileService;
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    profileService.addCreditCard(request, response);
    userService.getUser(request, response);
    return Pages.CREDIT_CARD_PAGE;
  }
}
