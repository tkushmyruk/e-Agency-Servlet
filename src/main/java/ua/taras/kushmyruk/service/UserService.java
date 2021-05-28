package ua.taras.kushmyruk.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

  void loginUser(HttpServletRequest request, HttpServletResponse response);

  void registrateUser(HttpServletRequest request, HttpServletResponse response);

  void logoutUser(HttpServletRequest request);

}
