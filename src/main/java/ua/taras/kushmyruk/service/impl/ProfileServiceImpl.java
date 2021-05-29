package ua.taras.kushmyruk.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.taras.kushmyruk.dao.UserDao;
import ua.taras.kushmyruk.dao.impl.UserDaoImpl;
import ua.taras.kushmyruk.service.ProfileService;
import ua.taras.kushmyruk.util.Parameters;

public class ProfileServiceImpl implements ProfileService {
  private static final UserDao userDao = new UserDaoImpl();

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
}

