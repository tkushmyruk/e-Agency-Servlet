package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.util.Pages;

public class RedactTourCommand implements Command {
  private final TourService tourService;

  public RedactTourCommand(TourService tourService) {
    this.tourService = tourService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    tourService.redactTour(request, response);
    tourService.getTourByTourName(request, response);
    return Pages.REDACT_TOUR_PAGE;
  }
}
