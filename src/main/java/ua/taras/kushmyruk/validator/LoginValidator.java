package ua.taras.kushmyruk.validator;

import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class LoginValidator {

  public void validateEmptyCredentials(String username, String password){
    if(username == null || username.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USERNAME_EMPTY_ERROR));
    }
    if(password == null || password.trim().equals("")){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_EMPTY_ERROR));
    }
  }

  public void validateUser(User user, String password){
    if(!user.getPassword().equals(password)){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.WRONG_PASSWORD_ERROR));
    }
    if(!user.isActive()){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USER_BLOCKED_ERROR));
    }
  }
}
