package ua.taras.kushmyruk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import ua.taras.kushmyruk.dao.ConnectionBuilder;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.RoomType;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.TourStatus;
import ua.taras.kushmyruk.model.TourType;
import ua.taras.kushmyruk.model.User;

public class TourDaoImpl implements TourDao {
  private static final String INSERT_TOUR = "INSERT INTO tour(tour_name, count_of_people, price,"
      + "start_date, end_date, departing_from, country, locality, is_all_inclusive, is_hot, hotel_name)"
      + "VALUES(?,?,?,?,?,"
      + "?,?,?,?,?,"
      + "?);";

  private static final String INSERT_TOUR_TYPE = "INSERT INTO tour_type (tour_id, tour_type)"
      + "VALUES(?, ?);";

  private static final String INSERT_ROOM_TYPE = "INSERT INTO room_type(tour_id, room_type)"
      + "VALUES(?, ?);";

  private static final String INESRT_TOUR_STATUS = "INSERT INTO tour_status(tour_id, tour_status)"
      + "VALUES(?, ?);";

  private static final String INSERT_HOTEL_STARS = "INSERT INTO hotel_stars(tour_id, hotel_stars)"
      + "VALUES(?, ?);";

  private static final String GET_TOUR = "SELECT * FROM tour WHERE tour_name = ?;";

  private static final String GET_TOUR_TYPE = "SELECT * FROM tour_type WHERE tour_name = ?;";

  private static final String GET_TOUR_STATUS = "SELECT * FROM tour_status WHERE tour_name = ?;";

  private static final String GET_ROOM_TYPE = "SELECT * FROM room_type WHERE tour_name = ?;";

  private static final String GET_HOTEL_STARS = "SELECT * FROM hotel_stars WHERE tour_name = ?;";

  private Connection getConnection() throws SQLException {
    return ConnectionBuilder.getConnection();
  }

  @Override
  public Tour findTourByTourName (String tourName){
    Tour tour = null;
    try(Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(GET_TOUR)) {
      statement.setString(1, tourName);
      ResultSet resultSet = statement.executeQuery();
      tour = new Tour();
      tour.setTourName(resultSet.getString("tour_name"));
      tour.setPrice(resultSet.getString("price"));
      tour.setCountOfPeople(resultSet.getInt("count_of_people"));
      tour.setStartDate(resultSet.getDate("start_date").toLocalDate());
      tour.setEndDate(resultSet.getDate("end_date").toLocalDate());
      tour.setLocality(resultSet.getString("locality"));
      tour.setCountry(resultSet.getString("country"));
      tour.setHotelName(resultSet.getString("hotel_name"));
      tour.setAllInclusive(resultSet.getBoolean("is_all_inclusive"));
      tour.setHot(resultSet.getBoolean("is_hot"));
      tour.setTourStatus(getTourStatus(connection, tourName));
      tour.setTourType(getTourType(connection, tourName));
      tour.setRoomType(getRoomType(connection, tourName));
      tour.setHotelStars(getHotelStars(connection, tourName));


    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tour;
  }

  private TourType getTourType(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_TOUR_TYPE);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    return TourType.valueOf(resultSet.getString("tour_type"));
  }
  private TourStatus getTourStatus(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_TOUR_TYPE);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    return TourStatus.valueOf(resultSet.getString("tour_status"));
  }
  private RoomType getRoomType(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_ROOM_TYPE);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    return RoomType.valueOf(resultSet.getString("room_type"));
  }
  private HotelStars getHotelStars(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_HOTEL_STARS);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    return HotelStars.valueOf(resultSet.getString("hotel_stars"));
  }

  @Override
  public void saveTour(Long id, String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality,
      String tourType, String roomType, String tourStatus, String hotelStars, String hotelName,
      boolean isAllInclusive, boolean isHot, User user) {



  }
}
