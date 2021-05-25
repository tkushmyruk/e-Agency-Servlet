package ua.taras.kushmyruk.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.taras.kushmyruk.util.CommandNames;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class LoginFilter implements Filter {
  private List<String> notAllowedActions = new ArrayList<>();

  @Override
  public void init(FilterConfig fc) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession(false);

    boolean notAllowedRequest = isNotAllowedRequest(request);
    if (notAllowedRequest && (session == null || session.getAttribute(Parameters.ROLE) == null)) {
      RequestDispatcher dispatcher = request.getRequestDispatcher(Pages.INDEX_PAGE);
      dispatcher.forward(request, response);
    } else {
      chain.doFilter(request, response);
    }
  }

  private boolean isNotAllowedRequest(ServletRequest request) {
    String command = request.getParameter(CommandNames.PARAMETER_COMMAND);
    return command != null && notAllowedActions.contains(command);
  }

  @Override
  public void destroy() {

  }

}
