package ua.taras.kushmyruk.validator;

import java.time.LocalDate;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class TourValidator {

  public void validateTourInformation(String tourName, int countOfPeople, String price,
      LocalDate startDate, LocalDate endDate, String departingFrom, String country, String locality){
    if(tourName == null || tourName.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.TOUR_NAME_EMPTY_ERROR));
    }
    if(countOfPeople != 0){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.COUNT_OF_PEOPLE_EMPTY_ERROR));
    }
    if(price == null || price.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.PRICE_EMPTY_ERROR));
    }
    if(startDate == null){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.START_DATE_EMPTY_ERROR));
    }
    if(startDate.isBefore(LocalDate.now())){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.START_DATE_BEFORE_NOW_ERROR));
    }
    if(endDate == null){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.END_DATE_EMPTY_ERROR));
    }
    if(endDate.isBefore(startDate)){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.END_DATE_BEFORE_START_DATE_ERROR));
    }
    if(departingFrom == null || departingFrom.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.DEPARTING_FROM_EMPTY_ERROR));
    }
    if(country == null || country.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.COUNTRY_EMPTY_ERROR));
    }
    if(locality == null || locality.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.LOCALITY_EMPTY_ERROR));
    }
  }

  public void validateBuyingTour(double balanceOnCard, double price){
    if(balanceOnCard - price < 0) {
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.NOT_ENOUGH_MONEY_ERROR));
    }
  }

}
