package ua.taras.kushmyruk.command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.taras.kushmyruk.exception.AppException;
import ua.taras.kushmyruk.service.ServiceFactory;
import ua.taras.kushmyruk.util.CommandNames;

public class CommandFactory {
  private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
  private Map<String, Command> commandMap = new HashMap<>();
  private ServiceFactory serviceFactory = ServiceFactory.getInstance();

  public CommandFactory() {
    commandMap.put(CommandNames.INDEX_COMMAND, new DefaultCommand());
    commandMap.put(CommandNames.LOGIN_COMMAND, new LoginCommand(serviceFactory.getUserService()));
    commandMap.put(CommandNames.REGISTRATION_COMMAND, new RegistrationCommand(serviceFactory.getUserService()));
    commandMap.put(CommandNames.LANGUAGE_COMMAND, new LanguageCommand());
    commandMap.put(CommandNames.REDIRECT_REGISTRATION_COMMAND, new RedirectCommand(CommandNames.REGISTRATION_COMMAND));
    commandMap.put(CommandNames.REDIRECT_LOGIN_COMMAND, new RedirectCommand(CommandNames.LOGIN_COMMAND));
    commandMap.put(CommandNames.LOGOUT, new LogoutCommand(serviceFactory.getUserService()));
    commandMap.put(CommandNames.TOUR_LIST_COMMAND, new TourListCommand(serviceFactory.getTourService()));
    commandMap.put(CommandNames.ADD_TOUR_COMMAND, new AddTourCommand(serviceFactory.getTourService()));
    commandMap.put(CommandNames.REDIRECT_ADD_TOUR_COMMAND, new RedirectCommand(CommandNames.ADD_TOUR_COMMAND));
    commandMap.put(CommandNames.TOUR_INFO_COMMAND, new TourInfoCommand(serviceFactory.getTourService()));

  }

  private static class CommandFactoryHolder {
    private static final CommandFactory instance = new CommandFactory();
  }

  public static CommandFactory getInstance() {
    return CommandFactoryHolder.instance;
  }

  public String invoke(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
    String commandName = request.getParameter(CommandNames.PARAMETER_COMMAND);
    LOGGER.info(CommandNames.PARAMETER_COMMAND + " " + commandName);
    System.out.println(commandName);
    Command command = commandMap.get(commandName);
    if (command == null) {
      command = new DefaultCommand();
    }
    try {
      return command.execute(request, response);
    } catch (AppException e) {
      return command.doOnError(request, e);
    }
  }
}
