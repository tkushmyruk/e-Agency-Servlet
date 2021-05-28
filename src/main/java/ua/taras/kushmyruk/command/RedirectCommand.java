package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.util.Parameters;

public class RedirectCommand  implements Command {
  private String path;

  public RedirectCommand(String path) {
    this.path = Parameters.PATH_PREFIX + path + Parameters.PATH_SUFFIX;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
    return path;
  }

}
