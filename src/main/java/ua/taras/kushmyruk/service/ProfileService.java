package ua.taras.kushmyruk.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProfileService {

  void getUserList(HttpServletRequest request, HttpServletResponse response);

  void userEdit(HttpServletRequest request, HttpServletResponse response);

}
