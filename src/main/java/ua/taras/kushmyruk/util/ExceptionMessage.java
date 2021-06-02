package ua.taras.kushmyruk.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ExceptionMessage {
  public static final String MESSAGE_ERROR = "messageError";
  public static final String ERROR_PAGE = "Error page found ";
  public static final String USERNAME_EMPTY_ERROR = "usernameEmptyError";
  public static final String PASSWORD_EMPTY_ERROR = "passwordEmptyError";
  public static final String WRONG_PASSWORD_ERROR = "wrongPasswordError";
  public static final String USER_BLOCKED_ERROR = "userBlockedError";
  public static final String EMAIL_EMPTY_ERROR = "emailEmptyError";
  public static final String EMAIL_PATTERN_ERROR = "emailPatternError";
  public static final String USER_EXISTS_ERROR = "userExistsError";
  public static final String CREDIT_CARD_EXISTS_ERROR = "creditCardExistsError";
  public static final String CARD_NUMBER_EMPTY_ERROR = "cardNumberEmptyError";
  public static final String CARD_PASSWORD_EMPTY_ERROR = "cardPasswordEmptyError";
  public static final String CARD_NUMBER_PATTERN_ERROR = "cardNumberPatternError";
  public static final String NOT_CORRECT_CARD_PASSWORD_ERROR = "notCorrectCardPasswordError";
  public static final String BALANCE_EMPTY_ERROR = "balanceEmptyError";

  public static final Locale ENGLISH = new Locale("en", "US");
  public static final Locale UKRAINIAN = new Locale("uk", "UA");

  private static final String BUNDLE_NAME = "/i18n/exception";
  private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

  public static void setLocale(Locale locale) {
    System.out.println(locale.toString());
    resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
  }

  public static String getMessage(String key) {
    return resourceBundle.getString(key);
  }

}
