package ua.taras.kushmyruk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationCommandTest {
  private static UserService userService;
  private static RegistrationCommand registrationCommand;
  private static HttpServletRequest request;
  private static HttpServletResponse response;

  @BeforeClass
  public static void doInitialization(){
    userService = mock(UserServiceImpl.class);
    registrationCommand = new RegistrationCommand(userService);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
  }

  @Test
  public void testRegistration(){
    when(request.getParameter(Parameters.USERNAME)).thenReturn("username");
    when(request.getParameter(Parameters.PASSWORD)).thenReturn("password");
    when(request.getParameter(Parameters.EMAIL)).thenReturn("email@gmail.com");
    String path = registrationCommand.execute(request, response);
    assertNotNull(path);
    assertEquals(Pages.LOGIN_PAGE, path);
  }

  @Test
  public void testDoOnErrorRegistration(){
    when(request.getParameter(Parameters.USERNAME)).thenReturn("");
    when(request.getParameter(Parameters.PASSWORD)).thenReturn("password");
    when(request.getParameter(Parameters.EMAIL)).thenReturn("email@gmail.com");
    String path = registrationCommand.doOnError(request, new DaoException());
    assertNotNull(path);
    assertEquals(Pages.REGISTRATION_PAGE, path);
  }

}
