package ua.taras.kushmyruk.taglib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.util.LoggerMessage;

public class DateFormatTag extends TagSupport {
  private static final Logger LOGGER = Logger.getLogger(DateFormatTag.class);

  private String pattern;
  private LocalDate date;

  public void setDate(String date) {
    this.date = LocalDate.parse(date);
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public int doStartTag() throws JspException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
    String formattedDate = date.format(formatter);
    try {
      pageContext.getOut().write(formattedDate);
    } catch (Exception e) {
      LOGGER.error(LoggerMessage.EXCEPTION_MESSAGE);
    }
    return SKIP_BODY;
  }

}
