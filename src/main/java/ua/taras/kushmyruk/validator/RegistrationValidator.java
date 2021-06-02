package ua.taras.kushmyruk.validator;

import java.util.regex.Pattern;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.util.ExceptionMessage;

public class RegistrationValidator {
  private static final String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

  public void validateCredentrials(String username, String password, String email) {
    if (username == null || username.trim() == "") {
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USERNAME_EMPTY_ERROR));
    }
    if (password == null || password.trim() == "") {
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_EMPTY_ERROR));
    }
    if (email == null || email.trim() == "") {
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_EMPTY_ERROR));
    }
    boolean pattern = Pattern.matches(emailRegex, email);
    if (!pattern) {
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_PATTERN_ERROR));
    }
  }

  public void validateIfUserExists(User user){
    if(user != null){
      throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.USER_EXISTS_ERROR));
    }
  }
}
