package ua.taras.kushmyruk.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface UserService {

  void loginUser(ServletRequest request, ServletResponse response);

  void registrateUser(ServletRequest request, ServletResponse response);

}
