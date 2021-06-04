package ua.taras.kushmyruk.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
  private static CreditCardValidation creditCardValidation = new CreditCardValidation();
  private static final UserDao userDao = new UserDaoImpl();
  private static final TourDao tourDao = new TourDaoImpl();

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
    request.setAttribute(Parameters.USER_LIST, userDao.findAllUsers());
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
    userDao.setBlock(request.getParameter(Parameters.USERNAME));
  }

  private void activeUser(HttpServletRequest request, HttpServletResponse response){
    userDao.setActive(request.getParameter(Parameters.USERNAME));
  }

  private void changeRoleUser(HttpServletRequest request, HttpServletResponse response){
    userDao.changeUserRole(request.getParameter(Parameters.USERNAME), request.getParameter(Parameters.ROLE));
  }

  @Override
  public void addCreditCard(HttpServletRequest request, HttpServletResponse response) {
    String username = (String) request.getSession().getAttribute(Parameters.USER_AUTH);
    String cardNumber = request.getParameter(Parameters.CARD_NUMBER);
    String cardPassword = request.getParameter(Parameters.CARD_PASSWORD);
    User user = userDao.findUserByUsername(username);
    creditCardValidation.validateCreditCard(user, cardNumber, cardPassword);
    userDao.setCreditCard(username, cardNumber, cardPassword);
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

