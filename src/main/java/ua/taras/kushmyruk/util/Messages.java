package ua.taras.kushmyruk.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Messages {
  public static final String successMessage = "Registration success: ";

  public static final Locale ENGLISH = new Locale("en", "US");
  public static final Locale UKRAINIAN = new Locale("uk", "UA");

  private static final String BUNDLE_NAME = "/i18n/messages";
  private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

  public static void setLocale(Locale locale) {
    System.out.println("START SET LOCALE");
    resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    System.out.println("RESOURCE BUNDLE");
  }

  public static String getMessage(String key) {
    return resourceBundle.getString(key);
  }

}
