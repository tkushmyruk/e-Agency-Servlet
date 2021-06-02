package ua.taras.kushmyruk.validator;

import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class LoginValidator {

  public void validateLogin(String username, String password){
    if(username == null || username.trim() == ""){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USERNAME_EMPTY_ERROR));
    }
    if(password == null || password.trim() == ""){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_EMPTY_ERROR));
    }

  }

  public void validatePassword(String username, String password, String email, User user){

  }


}
