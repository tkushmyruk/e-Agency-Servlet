package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.util.Pages;

public class ReturnTourCommand implements Command {
  private final TourService tourService;
  private final UserService userService;

  public ReturnTourCommand(TourService tourService,
      UserService userService) {
    this.tourService = tourService;
    this.userService = userService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    tourService.returnTour(request, response);
    userService.getUser(request, response);
    return Pages.USER_PROFILE_PAGE;
  }
}
