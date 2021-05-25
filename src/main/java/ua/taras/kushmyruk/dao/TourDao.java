package ua.taras.kushmyruk.dao;

import java.time.LocalDate;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;

public interface TourDao {

  void saveTour(Long id,String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality,
      String tourType, String roomType, String tourStatus, String hotelStars, String hotelName,
      boolean isAllInclusive, boolean isHot, User user);

  Tour findTourByTourName (String tourName);

}
