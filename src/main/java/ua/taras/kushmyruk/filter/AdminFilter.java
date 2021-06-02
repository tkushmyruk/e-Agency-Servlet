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
import ua.taras.kushmyruk.model.UserRole;
import ua.taras.kushmyruk.util.CommandNames;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class AdminFilter implements Filter {
  private List<String> notAllowedActions = new ArrayList<>();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    notAllowedActions.add(CommandNames.LOGIN_COMMAND);
    notAllowedActions.add(CommandNames.USER_PROFILE_COMMAND);
    notAllowedActions.add(CommandNames.CREDIT_CARD_COMMAND);
    notAllowedActions.add(CommandNames.ADD_CREDIT_CARD_COMMAND);
    notAllowedActions.add(CommandNames.BUY_TOUR_COMMAND);
    notAllowedActions.add(CommandNames.BOUGHT_TOUR_LIST_COMMAND);
    notAllowedActions.add(CommandNames.REPLENISH_COMMAND);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession(false);
    String role = null;
    if (session != null) {
      role = (String) session.getAttribute(Parameters.ROLE);
    }
    boolean notAllowedRequest = isNotAllowedRequest(request);

    if (notAllowedRequest && ((role != null && role.equals(UserRole.ADMIN.toString())))) {
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
