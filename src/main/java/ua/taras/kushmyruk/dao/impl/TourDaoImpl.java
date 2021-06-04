package ua.taras.kushmyruk.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ua.taras.kushmyruk.dao.ConnectionBuilder;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.RoomType;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.TourStatus;
import ua.taras.kushmyruk.model.TourType;

public class TourDaoImpl implements TourDao {
  private static final String INSERT_TOUR = "INSERT INTO tour(tour_name, count_of_people, price,"
      + "start_date, end_date, departing_from, country, locality, is_all_inclusive, is_hot,"
      + " hotel_name)"
      + "VALUES(?,?,?,?,?,"
      + "?,?,?,?,?,"
      + "?);";

  private static final String INSERT_TOUR_TYPE = "INSERT INTO tour_type (tour_name, tour_type)"
      + "VALUES(?, ?);";

  private static final String INSERT_ROOM_TYPE = "INSERT INTO room_type(tour_name, room_type)"
      + "VALUES(?, ?);";

  private static final String INESRT_TOUR_STATUS = "INSERT INTO tour_status(tour_name, tour_status)"
      + "VALUES(?, ?);";

  private static final String INSERT_HOTEL_STARS = "INSERT INTO hotel_stars(tour_name, hotel_stars)"
      + "VALUES(?, ?);";

  private static final String GET_TOURS_BY_TOUR_STATUS = "SELECT * FROM tour t "
      + "INNER JOIN tour_type tt ON tt.tour_name = t.tour_name "
      + "INNER JOIN tour_status ts ON ts.tour_name = t.tour_name "
      + "INNER JOIN room_type rt ON rt.tour_name = t.tour_name "
      + "INNER JOIN hotel_stars hs ON hs.tour_name = t.tour_name "
      + "WHERE ts.tour_status = 'REGISTERED'";

  private static final String GET_USER_BOUGHT_TORUS = "SELECT * FROM tour t "
      + "INNER JOIN tour_type tt ON tt.tour_name = t.tour_name "
      + "INNER JOIN tour_status ts ON ts.tour_name = t.tour_name "
      + "INNER JOIN room_type rt ON rt.tour_name = t.tour_name "
      + "INNER JOIN hotel_stars hs ON hs.tour_name = t.tour_name "
      + "WHERE t.username = ?;";

  private static final String GET_TOUR = "SELECT * FROM tour WHERE tour_name = ?;";

  private static final String GET_TOUR_TYPE = "SELECT * FROM tour_type WHERE tour_name = ?;";

  private static final String GET_TOUR_STATUS = "SELECT * FROM tour_status WHERE tour_name = ?;";

  private static final String GET_ROOM_TYPE = "SELECT * FROM room_type WHERE tour_name = ?;";

  private static final String GET_HOTEL_STARS = "SELECT * FROM hotel_stars WHERE tour_name = ?;";

  private static final String SET_USER_FOR_TOUR = "UPDATE tour SET username = ? WHERE tour_name = ?; ";


  private static final String UPDATE_TOUR_STATUS = "UPDATE tour_status SET tour_status = ?"
      + " WHERE tour_name = ?;";

  private static final String UPDATE_TOUR = "UPDATE tour SET count_of_people = ?, price = ?, start_date = ?, "
      + "end_date = ?, departing_from = ?, locality = ?, country = ?, is_all_inclusive = ?, is_hot = ?, "
      + " hotel_name = ? WHERE tour_name = ?;";

  private static final String UPDATE_TOUR_TYPE = "UPDATE tour_type SET tour_type = ? WHERE tour_name = ?";

  private static final String UPDATE_ROOM_TYPE = "UPDATE room_type SET room_type = ? WHERE tour_name = ?";

  private static final String UPDATE_HOTEL_STARS = "UPDATE hotel_stars SET hotel_stars = ? WHERE tour_name = ?";

  private static final String DELETE_TOUR = "DELETE tour WHERE tour_name  = ?;";

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
      resultSet.next();
      tour = new Tour();
      tour.setTourName(resultSet.getString("tour_name"));
      tour.setPrice(resultSet.getString("price"));
      tour.setCountOfPeople(resultSet.getInt("count_of_people"));
      tour.setStartDate(resultSet.getDate("start_date").toLocalDate());
      tour.setEndDate(resultSet.getDate("end_date").toLocalDate());
      tour.setDepartingFrom(resultSet.getString("departing_from"));
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
      System.out.println(e.getMessage());
    }
    return tour;
  }

  @Override
  public List<Tour> findAllNotBoughtTours() {
    List<Tour> tours = new ArrayList<>();
    try(Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(GET_TOURS_BY_TOUR_STATUS)) {
      getTourList(tours, statement);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tours;
  }

  private TourType getTourType(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_TOUR_TYPE);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return TourType.valueOf(resultSet.getString("tour_type"));
  }
  private TourStatus getTourStatus(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_TOUR_STATUS);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return TourStatus.valueOf(resultSet.getString("tour_status"));
  }
  private RoomType getRoomType(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_ROOM_TYPE);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return RoomType.valueOf(resultSet.getString("room_type"));
  }
  private HotelStars getHotelStars(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(GET_HOTEL_STARS);
    statement.setString(1, tourName);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return HotelStars.valueOf(resultSet.getString("hotel_stars"));
  }

  @Override
  public boolean saveTour(String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality,
      String tourType, String roomType, String hotelStars, String hotelName,
      boolean isAllInclusive, boolean isHot) {

    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(INSERT_TOUR, new String[]{"tour_id"});
      connection.setAutoCommit(false);
      try{
        statement.setString(1, tourName);
        statement.setInt(2, countOfPeople);
        statement.setDouble(3, Double.valueOf(price));
        statement.setDate(4, Date.valueOf(startDate));
        statement.setDate(5, Date.valueOf(endDate));
        statement.setString(6, departingFrom);
        statement.setString(7, country);
        statement.setString(8, locality);
        statement.setBoolean(9, isAllInclusive);
        statement.setBoolean(10, isHot);
        statement.setString(11, hotelName);
        statement.executeUpdate();
        saveTourType(connection,tourName, tourType);
        saveTourStatus(connection, tourName);
        saveRoomType(connection, tourName, roomType);
        saveHotelStars(connection, tourName, hotelStars);
        connection.commit();
        return true;
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private void saveTourType(Connection connection, String tourName, String tourType) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(INSERT_TOUR_TYPE);
    statement.setString(1, tourName);
    statement.setString(2, tourType);
    statement.executeUpdate();
  }

  private void saveTourStatus(Connection connection, String tourName) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(INESRT_TOUR_STATUS);
    statement.setString(1, tourName);
    statement.setString(2, TourStatus.REGISTERED.toString());
    statement.executeUpdate();
  }

  private void saveRoomType(Connection connection, String tourName, String roomType) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(INSERT_ROOM_TYPE);
    statement.setString(1, tourName);
    statement.setString(2, roomType);
    statement.executeUpdate();
  }

  private void saveHotelStars(Connection connection, String tourName, String hotelStars) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(INSERT_HOTEL_STARS);
    statement.setString(1, tourName);
    statement.setString(2, hotelStars);
    statement.executeUpdate();
  }

  @Override
  public boolean setUserForTour(String tourName, String username) {
    boolean result = false;
    try(Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement(SET_USER_FOR_TOUR)) {
      connection.setAutoCommit(false);
      try {
        statement.setString(1, username);
        statement.setString(2, tourName);
        statement.executeUpdate();
        updateTourStatus(tourName, "BOUGHT", connection);
        connection.commit();
        result = true;
      } catch (Exception e) {
        connection.rollback();
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private boolean updateTourStatus(String tourName, String status, Connection connection)
      throws SQLException {
    PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR_STATUS);
    statement.setString(1, status);
    statement.setString(2, tourName);
    int executeUpdate = statement.executeUpdate();
    return executeUpdate >= 0;
  }

  @Override
  public List<Tour> findAllBoughtToursByUsername(String username) {
    List<Tour> tours = new ArrayList<>();
    try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_BOUGHT_TORUS)) {
      statement.setString(1, username);
      getTourList(tours, statement);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tours;
  }

  private void getTourList(List<Tour> tours, PreparedStatement statement) throws SQLException {
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()){
      Tour tour = new Tour();
      tour.setTourName(resultSet.getString("tour_name"));
      tour.setPrice(resultSet.getString("price"));
      tour.setCountOfPeople(resultSet.getInt("count_of_people"));
      tour.setStartDate(resultSet.getDate("start_date").toLocalDate());
      tour.setEndDate(resultSet.getDate("end_date").toLocalDate());
      tour.setDepartingFrom(resultSet.getString("departing_from"));
      tour.setLocality(resultSet.getString("locality"));
      tour.setCountry(resultSet.getString("country"));
      tour.setHotelName(resultSet.getString("hotel_name"));
      tour.setAllInclusive(resultSet.getBoolean("is_all_inclusive"));
      tour.setHot(resultSet.getBoolean("is_hot"));
      tour.setTourType(TourType.valueOf(resultSet.getString("tour_type")));
      tour.setTourStatus(TourStatus.valueOf(resultSet.getString("tour_status")));
      tour.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
      tour.setHotelStars(HotelStars.valueOf(resultSet.getString("hotel_stars")));
      tours.add(tour);
    }
  }

  @Override
  public  boolean updateTour(String tourName ,int countOfPeople, String price, LocalDate startDate, LocalDate endDate,
      String departingFrom, String country, String locality, String tourType, String roomType,
      String hotelStars, String hotelName, boolean isAllInclusive, boolean isHot) {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR);
      connection.setAutoCommit(false);
      try{
        statement.setInt(1, countOfPeople);
        statement.setDouble(2, Double.valueOf(price));
        statement.setDate(3, Date.valueOf(startDate));
        statement.setDate(4, Date.valueOf(endDate));
        statement.setString(5, departingFrom);
        statement.setString(6, locality);
        statement.setString(7, country);
        statement.setBoolean(8, isAllInclusive);
        statement.setBoolean(9, isHot);
        statement.setString(10, hotelName);
        statement.setString(11, tourName);
        statement.executeUpdate();
        updateTourType(connection, tourName, tourType);
        updateRoomType(connection, tourName, roomType);
        updateHotelStars(connection, tourName, hotelStars);
        connection.commit();
        return true;
      } catch (SQLException e) {
        System.out.println(e.getMessage());
        connection.rollback();
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private void updateTourType(Connection connection, String tourName, String tourType) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR_TYPE);
    statement.setString(1, tourType);
    statement.setString(2, tourName);
    statement.executeUpdate();
  }

  private void updateRoomType(Connection connection, String tourName, String roomType) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM_TYPE);
    statement.setString(1, roomType);
    statement.setString(2, tourName);
    statement.executeUpdate();
  }

  private void updateHotelStars(Connection connection, String tourName, String hotelStars) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(UPDATE_HOTEL_STARS);
    statement.setString(1, hotelStars);
    statement.setString(2, tourName);
    statement.executeUpdate();
  }

  @Override
  public boolean returnTour(String tourName) {
    try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(SET_USER_FOR_TOUR)) {
      connection.setAutoCommit(false);
      try {
        statement.setString(1, null);
        statement.setString(2, tourName);
        statement.executeUpdate();
        updateTourStatus(tourName, "REGISTERED", connection);
        connection.commit();
        return true;
      } catch (Exception e) {
        connection.rollback();
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean deleteTour(String tourName) {
    try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_TOUR)) {
      connection.setAutoCommit(false);
      try {
        statement.setString(1, tourName);
        statement.executeUpdate();
        updateTourStatus(tourName, "CANCELED", connection);
        connection.commit();
        return true;
      } catch (Exception e) {
        connection.rollback();
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
