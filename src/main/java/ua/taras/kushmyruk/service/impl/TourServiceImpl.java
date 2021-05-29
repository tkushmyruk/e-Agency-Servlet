package ua.taras.kushmyruk.service.impl;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.dao.impl.TourDaoImpl;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.util.Parameters;

public class TourServiceImpl implements TourService {
  private static final TourDao tourDao = new TourDaoImpl();

  public TourServiceImpl() {
  }

  private static class TourServiceHolder {
    private static final TourServiceImpl instance = new TourServiceImpl();
  }

  public static TourServiceImpl getInstance() {
    return TourServiceImpl.TourServiceHolder.instance;
  }


  @Override
  public void getNotBoughtTours(HttpServletRequest request, HttpServletResponse response) {
    List<Tour> allNotBoughtTours = tourDao.findAllNotBoughtTours();
    request.setAttribute( Parameters.NOT_BOUGHT_TOURS, allNotBoughtTours);
  }

  @Override
  public void getTourByTourName(HttpServletRequest request, HttpServletResponse response) {
    Tour tourByTourName = tourDao.findTourByTourName(request.getParameter(Parameters.TOUR_NAME));
        request.setAttribute(Parameters.TOUR, tourByTourName);
  }

  @Override
  public void addTour(HttpServletRequest request, HttpServletResponse response){
    String tourName = request.getParameter(Parameters.TOUR_NAME);
    String price = request.getParameter(Parameters.PRICE);
    int countOfPeople = Integer.valueOf(request.getParameter(Parameters.COUNT_OF_PEOPLE));
    LocalDate startDate = LocalDate.parse(request.getParameter(Parameters.START_DATE));
    LocalDate endDate = LocalDate.parse(request.getParameter(Parameters.END_DATE));
    String departingFrom = request.getParameter(Parameters.DEPARTING_FROM);
    String locality = request.getParameter(Parameters.LOCALITY);
    String country = request.getParameter(Parameters.COUNTRY);
    String hotelName = request.getParameter(Parameters.HOTEL_NAME);
    String tourType = request.getParameter(Parameters.TOUR_TYPE);
    String roomType = request.getParameter(Parameters.ROOM_TYPE);
    String hotelStars = request.getParameter(Parameters.HOTEL_STARS);
    boolean isAllInclusive = request.getParameter(Parameters.IS_ALL_INCLUSIVE) != null;
    boolean isHot = request.getParameter(Parameters.IS_HOT) != null;
    tourDao.saveTour(tourName,countOfPeople, price, startDate, endDate, departingFrom, country, locality,
        tourType, roomType, hotelStars, hotelName, isAllInclusive, isHot);
  }

  @Override
  public void buyTour(HttpServletRequest request, HttpServletResponse response) {
    String tourName = request.getParameter(Parameters.TOUR_NAME);
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    boolean user = tourDao.setUserForTour(tourName, username);
  }
}
