package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.util.Pages;

public class BuyTourCommand implements Command {
  private final TourService tourService;

  public BuyTourCommand(TourService tourService) {
    this.tourService = tourService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    tourService.buyTour(request, response);
    tourService.getNotBoughtTours(request, response);
    return Pages.TOUR_LIST_PAGE;
  }
}
