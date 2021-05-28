package ua.taras.kushmyruk.service.impl;

import java.util.List;
import ua.taras.kushmyruk.dao.TourDao;
import ua.taras.kushmyruk.dao.impl.TourDaoImpl;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.service.TourService;

public class TourServiceImpl implements TourService {
  private static final TourDao tourDao = new TourDaoImpl();


  @Override
  public List<Tour> getNotBoughtTours() {
    return null;
  }
}
