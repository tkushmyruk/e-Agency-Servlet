package ua.taras.kushmyruk.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

  void loginUser(HttpServletRequest request, HttpServletResponse response);

  void registrateUser(HttpServletRequest request, HttpServletResponse response);

  void logoutUser(HttpServletRequest request);

  void changePassword(HttpServletRequest request, HttpServletResponse response);

  void getUser (HttpServletRequest request, HttpServletResponse response);

  void sendMessage(HttpServletRequest request, HttpServletResponse response);

  void getUserMessages(HttpServletRequest request, HttpServletResponse response);

}
