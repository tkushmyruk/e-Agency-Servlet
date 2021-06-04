package ua.taras.kushmyruk.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.taras.kushmyruk.exception.DaoException;
import ua.taras.kushmyruk.service.UserService;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;
import ua.taras.kushmyruk.util.Pages;
import ua.taras.kushmyruk.util.Parameters;

public class LoginCommandTest {
  private static UserService userService;
  private static LoginCommand loginCommand;
  private static HttpServletRequest request;
  private static HttpServletResponse response;


  @BeforeClass
  public static void doInitialization() {
    userService = mock(UserServiceImpl.class);
    loginCommand = new LoginCommand(userService);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
  }

  @Test
  public void testLogin(){
   when(request.getParameter(Parameters.USERNAME)).thenReturn("username");
   when(request.getParameter(Parameters.PASSWORD)).thenReturn("password");
   String path = loginCommand.execute(request, response);
    assertNotNull(path);
    Assert.assertEquals(Pages.INDEX_PAGE, path);
  }

  @Test
  public void testDoOnErrorRegistration(){
    when(request.getParameter(Parameters.USERNAME)).thenReturn("");
    when(request.getParameter(Parameters.PASSWORD)).thenReturn("password");
    String path = loginCommand.doOnError(request, new DaoException());
    assertNotNull(path);
    assertEquals(Pages.LOGIN_PAGE, path);
  }


}
