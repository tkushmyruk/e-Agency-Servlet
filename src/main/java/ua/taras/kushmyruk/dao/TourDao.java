package ua.taras.kushmyruk.dao;

import java.time.LocalDate;
import java.util.List;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;

public interface TourDao {

  void saveTour(String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality,
      String tourType, String roomType, String hotelStars, String hotelName,
      boolean isAllInclusive, boolean isHot);

  Tour findTourByTourName (String tourName);

  List<Tour> findAllNotBoughtTours();

  boolean setUserForTour(String tourName, String username);

  List<Tour> findAllBoughtToursByUsername (String username);


}
