package ua.taras.kushmyruk.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.command.CommandFactory;
import ua.taras.kushmyruk.util.ExceptionMessage;
import ua.taras.kushmyruk.util.LoggerMessage;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class MainServlet extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(MainServlet.class);

  private static CommandFactory commandFactory = CommandFactory.getInstance();

  @Override
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    System.out.println("PROCES");

    String page;
    try {
      page = commandFactory.invoke(request, response);
      request.getSession().setAttribute(Parameters.PAGE, page);
    } catch (RuntimeException e) {
      LOGGER.error(ExceptionMessage.ERROR_PAGE + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
      request.setAttribute(ExceptionMessage.MESSAGE_ERROR, e.getMessage());
      page = Pages.ERROR_PAGE;
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
    dispatcher.forward(request, response);
  }
}
