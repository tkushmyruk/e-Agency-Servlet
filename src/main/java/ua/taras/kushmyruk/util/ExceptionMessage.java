package ua.taras.kushmyruk.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ExceptionMessage {
  public static final String MESSAGE_ERROR = "messageError";
  public static final String ERROR_PAGE = "Error page found ";

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
