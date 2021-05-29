package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.util.Pages;

public class AdminProfileCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return Pages.ADMIN_PROFILE_PAGE;
  }
}
