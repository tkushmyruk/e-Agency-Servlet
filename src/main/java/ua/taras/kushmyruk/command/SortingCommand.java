package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;

public class SortingCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return null;
  }
}
