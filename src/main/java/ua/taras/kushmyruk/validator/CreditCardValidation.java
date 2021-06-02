package ua.taras.kushmyruk.validator;

import java.util.regex.Pattern;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class CreditCardValidation {
  private static final String cardNumberPassword =
      "(4[0-9]{12}(?:[0-9]{3})?)";
  public void validateCreditCard(User user, String cardNumber, String cardPassword){
    if(user.getCreditCard()!= null){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.CREDIT_CARD_EXISTS_ERROR));
    }
    if(cardNumber == null || cardNumber.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.CARD_NUMBER_EMPTY_ERROR));
    }
    if(cardPassword == null || cardPassword.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.CARD_PASSWORD_EMPTY_ERROR));
    }
    if(Pattern.matches(cardNumberPassword, cardNumber)){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.CARD_NUMBER_PATTERN_ERROR));
    }
  }

  public void validateReplenishCard(User user, String cardPassword, String balance){
    if(cardPassword == null || cardPassword.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.CARD_PASSWORD_EMPTY_ERROR));
    }
    if(!user.getPassword().equals(cardPassword)){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.NOT_CORRECT_CARD_PASSWORD_ERROR));
    }
    if(balance == null || balance.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.BALANCE_EMPTY_ERROR));
    }
  }
}
