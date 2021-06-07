package ua.taras.kushmyruk.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.dao.impl.TourDaoImpl;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.TourService;
import ua.taras.kushmyruk.service.serviceUtil.TourCountOfPeopleComparator;
import ua.taras.kushmyruk.service.serviceUtil.TourHotelStarsComparator;
import ua.taras.kushmyruk.service.serviceUtil.TourPriceComparator;
import ua.taras.kushmyruk.util.Parameters;
import ua.taras.kushmyruk.validator.TourValidator;

public class TourServiceImpl implements TourService {
  private static final TourValidator tourValidator = new TourValidator();
  private static final TourDao tourDao = new TourDaoImpl();
  private static final UserDao userDao = new UserDaoImpl();

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
    tourValidator.validateTourInformation(tourName, countOfPeople, price, startDate, endDate, departingFrom,
        country, locality);
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
    Tour tour = tourDao.findTourByTourName(tourName);
    User user = userDao.findUserByUsername(username);
    double price = Double.valueOf(tour.getPrice());
    double balanceOnCard = user.getCreditCard().getBalance();
    tourValidator.validateBuyingTour(balanceOnCard, price);
    userDao.updateCreditCardBalance(username, balanceOnCard - price);
    tourDao.setUserForTour(tourName, username);
  }

  @Override
  public void redactTour(HttpServletRequest request, HttpServletResponse response) {
    String redact = request.getParameter(Parameters.REDACT);
    if(redact.equals("true")){
      String tourName = request.getParameter(Parameters.TOUR_NAME);
      String price = request.getParameter(Parameters.PRICE);
      int countOfPeople = Integer.valueOf(request.getParameter(Parameters.COUNT_OF_PEOPLE));
      LocalDate startDate = LocalDate.parse(request.getParameter(Parameters.START_DATE));
      LocalDate endDate = LocalDate.parse(request.getParameter(Parameters.END_DATE));
      String departingFrom = request.getParameter(Parameters.DEPARTING_FROM);
      String locality = request.getParameter(Parameters.LOCALITY);
      String country = request.getParameter(Parameters.COUNTRY);
      tourValidator.validateTourInformation(tourName, countOfPeople, price, startDate, endDate, departingFrom,
          country, locality);
      String hotelName = request.getParameter(Parameters.HOTEL_NAME);
      String tourType = request.getParameter(Parameters.TOUR_TYPE);
      String roomType = request.getParameter(Parameters.ROOM_TYPE);
      String hotelStars = request.getParameter(Parameters.HOTEL_STARS);
      boolean isAllInclusive = request.getParameter(Parameters.IS_ALL_INCLUSIVE) != null;
      boolean isHot = request.getParameter(Parameters.IS_HOT) != null;
      tourDao.updateTour(tourName, countOfPeople, price,  startDate, endDate, departingFrom, country, locality,
           tourType, roomType, hotelStars, hotelName, isAllInclusive, isHot);
    }
  }

  @Override
  public void returnTour(HttpServletRequest request, HttpServletResponse response) {
    String tourName = request.getParameter(Parameters.TOUR_NAME);
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    Tour tour = tourDao.findTourByTourName(tourName);
    User user = userDao.findUserByUsername(username);
    double price = Double.valueOf(tour.getPrice());
    double balanceOnCard = user.getCreditCard().getBalance();
    balanceOnCard += price;
    userDao.updateCreditCardBalance(username, balanceOnCard);
    tourDao.returnTour(tourName);
  }

  @Override
  public void getSortedListOfTours(HttpServletRequest request, HttpServletResponse response) {
    String sortType = request.getParameter(Parameters.SORT_TYPE);
    List<Tour> tours = tourDao.findAllNotBoughtTours();
    switch (sortType){
      case "byTourType":
        sortByTourType(request, response, tours);
        break;
      case "byHotelStars":
        sortByHotelStars(request, response, tours);
        break;
      case "byPrice":
        sortByPrice(request, response, tours);
        break;
      case "byCountOfPeople":
        sortByCountOfPeople(request, response, tours);
        break;
    }
  }

  private void sortByTourType(HttpServletRequest request, HttpServletResponse response, List<Tour> tours){
    String tourType = request.getParameter(Parameters.TOUR_TYPE);
    List<Tour> sortedList = tours.stream()
        .filter(tour -> tour.getTourType().toString().equals(tourType))
        .collect(Collectors.toList());
    request.setAttribute(Parameters.NOT_BOUGHT_TOURS, sortedList);
  }

  private void sortByHotelStars(HttpServletRequest request, HttpServletResponse response, List<Tour> allTours){
    String direction = request.getParameter(Parameters.DIRECTION);
    TourHotelStarsComparator comparator = new TourHotelStarsComparator();
    if (direction != null && direction.equals("Asc")) {
      allTours.sort(comparator);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
    if(direction != null && direction.equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
  }

  private void sortByPrice(HttpServletRequest request, HttpServletResponse response, List<Tour> allTours){
    String direction = request.getParameter(Parameters.DIRECTION);
    TourPriceComparator comparator = new TourPriceComparator();
    if (direction.equals("Asc")) {
      allTours.sort(comparator);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
    if(direction.equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
  }

  private void sortByCountOfPeople(HttpServletRequest request, HttpServletResponse response, List<Tour> allTours){
    String direction = request.getParameter(Parameters.DIRECTION);
    TourCountOfPeopleComparator comparator = new TourCountOfPeopleComparator();
    if (direction.equals("Asc")) {
      allTours.sort(comparator);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
    if(direction.equals("Desc")){
      allTours.sort(comparator);
      Collections.reverse(allTours);
      request.setAttribute(Parameters.NOT_BOUGHT_TOURS, allTours);
    }
  }

  @Override
  public void cancelTour(HttpServletRequest request, HttpServletResponse response) {
    String tourName = request.getParameter(Parameters.TOUR_NAME);
    tourDao.deleteTour(tourName);
  }
}
