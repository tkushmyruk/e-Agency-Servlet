package ua.taras.kushmyruk.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoConfig {

  public static final String DB_URL = "db.url";
  public static final String DB_LOGIN = "db.username";
  public static final String DB_PASSWORD = "db.password";
  public static final String DB_LIMIT = "db.limit";

  private static Properties properties = new Properties();

  public synchronized static String getProperty(String name) {
    if (properties.isEmpty()) {
      try (InputStream stream = DaoConfig.class.getClassLoader()
          .getResourceAsStream("dao.properties")) {
        properties.load(stream);
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }

    }
    return properties.getProperty(name);

  }
}
