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
    setLocale(request);
    return request.getParameter(Parameters.PAGE);
  }

  private void setLocale(HttpServletRequest request) {
    String lang = request.getParameter(Parameters.LANGUAGE);
    LOGGER.info(LoggerMessage.LANGUAGE + lang);
    if (lang.equals(Parameters.EN)) {
      request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.EN_US);
      Messages.setLocale(Messages.ENGLISH);
      ExceptionMessage.setLocale(ExceptionMessage.ENGLISH);
    } else {
      request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.UK_UA);
      Messages.setLocale(Messages.UKRAINIAN);
      ExceptionMessage.setLocale(ExceptionMessage.UKRAINIAN);
    }
  }

}
