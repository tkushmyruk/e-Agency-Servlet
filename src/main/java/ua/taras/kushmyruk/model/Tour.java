package ua.taras.kushmyruk.model;

import java.time.LocalDate;
import java.util.Set;

public class Tour {
  private Long id;
  private String tourName;
  private int countOfPeople;
  private String price;
  private LocalDate startDate;
  private LocalDate endDate;
  private String departingFrom;
  private String country;
  private String locality;
  private TourType tourType;
  private RoomType roomType;
  private TourStatus tourStatus;
  private HotelStars hotelStars;
  private String hotelName;
  private boolean isAllInclusive;
  private boolean isHot;
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTourName() {
    return tourName;
  }

  public void setTourName(String tourName) {
    this.tourName = tourName;
  }

  public int getCountOfPeople() {
    return countOfPeople;
  }

  public void setCountOfPeople(int countOfPeople) {
    this.countOfPeople = countOfPeople;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getDepartingFrom() {
    return departingFrom;
  }

  public void setDepartingFrom(String departingFrom) {
    this.departingFrom = departingFrom;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }


  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public boolean isAllInclusive() {
    return isAllInclusive;
  }

  public void setAllInclusive(boolean allInclusive) {
    isAllInclusive = allInclusive;
  }

  public boolean isHot() {
    return isHot;
  }

  public void setHot(boolean hot) {
    isHot = hot;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TourType getTourType() {
    return tourType;
  }

  public void setTourType(TourType tourType) {
    this.tourType = tourType;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomType roomType) {
    this.roomType = roomType;
  }

  public TourStatus getTourStatus() {
    return tourStatus;
  }

  public void setTourStatus(TourStatus tourStatus) {
    this.tourStatus = tourStatus;
  }

  public HotelStars getHotelStars() {
    return hotelStars;
  }

  public void setHotelStars(HotelStars hotelStars) {
    this.hotelStars = hotelStars;
  }
}
