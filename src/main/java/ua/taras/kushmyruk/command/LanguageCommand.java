package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.util.ExceptionMessage;
import ua.taras.kushmyruk.util.LoggerMessage;
import ua.taras.kushmyruk.util.Messages;
import ua.taras.kushmyruk.util.Parameters;

public class LanguageCommand implements Command {
  private static final Logger LOGGER = Logger.getLogger(LanguageCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("LANG");
    setLocale(request);
    return request.getParameter(Parameters.PAGE);
  }

  private void setLocale(HttpServletRequest request) {
    String lang = request.getParameter(Parameters.LANGUAGE);
    System.out.println("param lang - " + lang);
    LOGGER.info(LoggerMessage.LANGUAGE + lang);
    if (lang.equals(Parameters.EN)) {
      System.out.println("EN");
      request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.EN_US);
      Messages.setLocale(Messages.ENGLISH);
      ExceptionMessage.setLocale(ExceptionMessage.ENGLISH);
    } else {
      System.out.println("UA");
      request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.UK_UA);
      System.out.println("In SESSION _ " + request.getSession().getAttribute(Parameters.LANGUAGE));
      Messages.setLocale(Messages.UKRAINIAN);
      System.out.println("AFTER SET LOCALE ");
      ExceptionMessage.setLocale(ExceptionMessage.UKRAINIAN);
      System.out.println("END UA");
    }
  }

}
