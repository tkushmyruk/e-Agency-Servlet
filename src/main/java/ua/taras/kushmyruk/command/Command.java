package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;

public interface Command {
  /**
   * executes some command, returns address to JSP page which will be forwarded by servlet to show results
   *
   * @param request  HTTP request from servlet
   * @param response HTTP response from servlet
   * @return address to JSP page which will be forwarded by servlet
   * @throws AppException if some exception occurs while performing some logic
   */
  String execute(HttpServletRequest request, HttpServletResponse response) throws AppException;

  /**
   * do some logic or throw new exception if exception occurred
   *
   * @param request HTTP request from servlet
   * @param e       exception occurred in method execute
   * @return address to JSP page with message about exception
   * @throws AppException if some exception occurs while preparing result page
   */
  default String doOnError(HttpServletRequest request, Exception e) throws AppException {
    throw new RuntimeException(e);
  }

}
