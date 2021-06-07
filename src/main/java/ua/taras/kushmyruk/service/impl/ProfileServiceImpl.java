package ua.taras.kushmyruk.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.dao.impl.TourDaoImpl;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.User;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.util.Parameters;
import ua.taras.kushmyruk.validator.CreditCardValidation;

public class ProfileServiceImpl implements ProfileService {
  private static final Logger LOGGER  = LoggerFactory.getLogger(ProfileServiceImpl.class);
  private static CreditCardValidation creditCardValidation = new CreditCardValidation();
  private static final UserDao userDao = new UserDaoImpl();
  private static final TourDao tourDao = new TourDaoImpl();
  private static final int PAGE_STEP = 4;

  public ProfileServiceImpl() {
  }

  private static class ProfileServiceHolder {
    private static final ProfileServiceImpl instance = new ProfileServiceImpl();
  }

  public static ProfileServiceImpl getInstance() {
    return ProfileServiceImpl.ProfileServiceHolder.instance;
  }


  @Override
  public void getUserList(HttpServletRequest request, HttpServletResponse response) {
    List<User> users = userDao.findAllUsers();
    int numberOfPages =  users.size() / PAGE_STEP + 1;
    int pageNumber = Integer.valueOf(request.getParameter(Parameters.PAGE_NUMBER));
    int startPosition = (pageNumber - 1) * PAGE_STEP;
    List<User> result = new ArrayList<>();
    for (int i = startPosition; i < startPosition + PAGE_STEP && i < users.size(); i++) {
      result.add(users.get(i));
    }
    request.setAttribute(Parameters.NUMBER_OF_PAGES, numberOfPages);
    request.setAttribute(Parameters.USER_LIST, result);
    request.setAttribute(Parameters.PAGE_NUMBER, pageNumber);
  }

  @Override
  public void userEdit(HttpServletRequest request, HttpServletResponse response) {
   String method = request.getParameter(Parameters.METHOD);
   switch (method){
     case "blockMethod": blockUser(request, response);
     break;
     case "activeMethod": activeUser(request, response);
     break;
     case "roleMethod": changeRoleUser(request, response);
   }
  }

  private void blockUser(HttpServletRequest request, HttpServletResponse response){
    String username = request.getParameter(Parameters.USERNAME);
    userDao.setBlock(username);
    LOGGER.info("User {} was blocked", username);
  }

  private void activeUser(HttpServletRequest request, HttpServletResponse response){
    String username = request.getParameter(Parameters.USERNAME);
    userDao.setActive(username);
    LOGGER.info("User {} was activated", username);

  }

  private void changeRoleUser(HttpServletRequest request, HttpServletResponse response){
    String username = request.getParameter(Parameters.USERNAME);
    String role = request.getParameter(Parameters.ROLE);
    userDao.changeUserRole(username, role);
    LOGGER.info("User {} changed role to {}", username , role);
  }

  @Override
  public void addCreditCard(HttpServletRequest request, HttpServletResponse response) {
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    String cardNumber = request.getParameter(Parameters.CARD_NUMBER);
    String cardPassword = request.getParameter(Parameters.CARD_PASSWORD);
    User user = userDao.findUserByUsername(username);
    creditCardValidation.validateCreditCard(user, cardNumber, cardPassword);
    userDao.setCreditCard(username, cardNumber, cardPassword);
    LOGGER.info("Users {} credit card with number {} ", username, cardNumber);
  }

  @Override
  public void replenishCard(HttpServletRequest request, HttpServletResponse response) {
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    String cardPassword = request.getParameter(Parameters.CARD_PASSWORD);
    String balanceFromRequest = request.getParameter(Parameters.REPLENISH);
    User user = userDao.findUserByUsername(username);
    creditCardValidation.validateReplenishCard(user, cardPassword, balanceFromRequest);
    double balance = Double.valueOf(balanceFromRequest);
    if(user.getCreditCard().getCardPassword().equals(cardPassword)) {
      double currentBalance = user.getCreditCard().getBalance();
      balance += currentBalance;
      userDao.updateCreditCardBalance(username, balance);
    }
  }

  @Override
  public void getBoughtTours(HttpServletRequest request, HttpServletResponse response) {
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    List<Tour> allBoughtToursByUsername = tourDao.findAllBoughtToursByUsername(username);
    request.setAttribute(Parameters.USER_TOURS, allBoughtToursByUsername);
  }
}

