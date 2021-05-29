package ua.taras.kushmyruk.service;

import ua.taras.kushmyruk.service.impl.TourServiceImpl;
import ua.taras.kushmyruk.service.impl.UserServiceImpl;

public class ServiceFactory {

  private ServiceFactory() {
  }

  private static class ServiceFactoryHolder {
    private static final ServiceFactory instance = new ServiceFactory();
  }

  public static ServiceFactory getInstance() {
    return ServiceFactoryHolder.instance;
  }

  public UserService getUserService(){return UserServiceImpl.getInstance(); }

  public TourService getTourService(){
    return TourServiceImpl.getInstance();
  }

}
