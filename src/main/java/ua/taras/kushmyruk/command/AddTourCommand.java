package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class AddTourCommand implements Command {
  private static final Logger LOGGER = LoggerFactory.getLogger(AddTourCommand.class);
  private final TourService tourService;

  public AddTourCommand(TourService tourService) {
    this.tourService = tourService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    tourService.addTour(request, response);
    return Pages.TOUR_LIST_PAGE;
  }

  @Override
  public String doOnError(HttpServletRequest request, Exception e) throws AppException {
    LOGGER.error(e.getMessage());
    request.setAttribute(Parameters.EXCEPTION, e.getMessage());
    return Pages.ADD_TOUR_PAGE;
  }
}
