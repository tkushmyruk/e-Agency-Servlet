package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class AddCreditCardCommand implements Command {
  private static final Logger LOGGER = LoggerFactory.getLogger(AddCreditCardCommand.class);
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

  @Override
  public String doOnError(HttpServletRequest request, Exception e) throws AppException {
    LOGGER.error(e.getMessage());
    request.setAttribute(Parameters.EXCEPTION, e.getMessage());
    return Pages.ADD_CREDIT_CARD_PAGE;
  }
}
