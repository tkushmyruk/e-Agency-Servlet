package ua.taras.kushmyruk.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.model.CreditCard;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.model.UserRole;

public class UserDaoTest {
  private UserDao userDao = new UserDaoImpl();

  @Before
  public  void init() throws IOException, URISyntaxException {
    DbInit.startup();
  }


  @Test
  public void findByUsernameTest(){
    User admin = userDao.findUserByUsername("admin");
    Assert.assertNotNull(admin);
    Assert.assertEquals("admin", admin.getUsername());
    Assert.assertEquals("admin", admin.getPassword());
    Assert.assertEquals("admin@gmail.com", admin.getEmail());

    User manager = userDao.findUserByUsername("manager");
    Assert.assertNotNull(manager);
    Assert.assertEquals("manager", manager.getUsername());
    Assert.assertEquals("manager", manager.getPassword());
    Assert.assertEquals("manager@gmail.com", manager.getEmail());
  }

  @Test
  public void userBlockAndActiveTest(){
    userDao.setBlock("manager");
    User blockedManager = userDao.findUserByUsername("manager");
    Assert.assertEquals(false, blockedManager.isActive());
    userDao.setActive("manager");
    User activatedManager = userDao.findUserByUsername("manager");
    Assert.assertEquals(true, activatedManager.isActive());
  }

  @Test
  public void findAllUsersTest(){
    List<User> allUsers = userDao.findAllUsers();
    Assert.assertNotNull(allUsers);
    Assert.assertEquals(10, allUsers.size());
  }

  @Test
  public void creditCardTest(){
    userDao.setCreditCard("manager", "2222 3333 4444 5555", "1234");
    userDao.updateCreditCardBalance("manager", 200.00);
    User userFromDb = userDao.findUserByUsername("manager");
    CreditCard creditCard = userFromDb.getCreditCard();
    Assert.assertNotNull(creditCard);
    Assert.assertEquals(200.00, creditCard.getBalance(), 0.01);
  }

  @Test
  public void roleTest(){
    userDao.changeUserRole("manager", UserRole.ADMIN.toString());
    User user = userDao.findUserByUsername("manager");
    String role = user.getRole().toString();
    Assert.assertNotNull(role);
    Assert.assertEquals("ADMIN", role);
  }

  @Test
  public void test(){
    userDao.updateUser("manager", "newPassword", "newEmail@gmail.com");
    User user = userDao.findUserByUsername("manager");
    Assert.assertEquals("newPassword", user.getPassword());
    Assert.assertEquals("newEmail@gmail.com", user.getEmail());
  }

}
