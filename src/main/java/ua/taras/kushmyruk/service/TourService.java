package ua.taras.kushmyruk.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TourService {

  void getNotBoughtTours(HttpServletRequest request, HttpServletResponse response);

  void addTour(HttpServletRequest request, HttpServletResponse response);

  void getTourByTourName(HttpServletRequest request, HttpServletResponse response);

  void buyTour(HttpServletRequest request, HttpServletResponse response);


}
