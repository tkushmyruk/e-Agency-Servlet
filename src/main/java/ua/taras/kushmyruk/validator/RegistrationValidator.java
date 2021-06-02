package ua.taras.kushmyruk.validator;

import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class RegistrationValidator {

  public void validateEmptyCredentrials(String username, String password, String email){
    if(username == null || username.trim() == ""){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USERNAME_EMPTY_ERROR));
    }
    if(password == null || password.trim() == ""){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_EMPTY_ERROR));
    }
    if(email == null || email.trim() == ""){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_EMPTY_ERROR));
    }
  }

}
