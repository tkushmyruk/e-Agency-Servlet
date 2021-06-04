package ua.taras.kushmyruk.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.taras.kushmyruk.dao.impl.TourDaoImpl;
import ua.taras.kushmyruk.model.HotelStars;
import ua.taras.kushmyruk.model.RoomType;
import ua.taras.kushmyruk.model.Tour;
import ua.taras.kushmyruk.model.TourType;

public class TourDaoTest {
  private TourDao tourDao = new TourDaoImpl();

  @Before
  public void init() throws IOException, URISyntaxException {
    DbInit.startup();
  }

  @Test
  public void findTourByTourNameTest(){
    Tour tour = tourDao.findTourByTourName("best_tour");
    Assert.assertNotNull(tour);
    Assert.assertEquals("best_tour", tour.getTourName());
  }

  @Test
  public void getAllToursTest(){
    List<Tour> tours = tourDao.findAllNotBoughtTours();
    Assert.assertNotNull(tours);
    Assert.assertEquals(3, tours.size());
  }

  @Test
  public void  saveTourTest(){
    tourDao.saveTour("new_tour", 2, "199.99", LocalDate.parse("2021-09-22"),
        LocalDate.parse("2021-10-22"), "kiev", "turkey", "antalia",
        TourType.EXCURSION.toString(), RoomType.LUXE.toString(), HotelStars.FOUR.toString(),
        "new_hotel", true ,true);
    Tour tourFromDb = tourDao.findTourByTourName("new_tour");
    Assert.assertEquals("new_tour", tourFromDb.getTourName());
    Assert.assertEquals("turkey", tourFromDb.getCountry());
    Assert.assertEquals(TourType.EXCURSION, tourFromDb.getTourType());
    Assert.assertEquals(RoomType.LUXE, tourFromDb.getRoomType());
    Assert.assertEquals(HotelStars.FOUR, tourFromDb.getHotelStars());
  }

  @Test
  public void deleteTest(){
    tourDao.deleteTour("best_tour");
    List<Tour> allNotBoughtTours = tourDao.findAllNotBoughtTours();
    List<Tour> ifListContainBestTour = allNotBoughtTours.stream()
        .filter(tour -> tour.getTourName().equals("best_tour"))
        .collect(Collectors.toList());
    boolean empty = ifListContainBestTour.isEmpty();
    Assert.assertEquals(true, empty);
  }

  @Test
  public void testBuyTour(){
    tourDao.setUserForTour("best_tour", "admin");
    List<Tour> adminsTours = tourDao.findAllBoughtToursByUsername("admin");
    boolean empty = adminsTours.isEmpty();
    Assert.assertEquals(false, empty);
    Assert.assertEquals(1, adminsTours.size());
    Assert.assertEquals("best_tour", adminsTours.get(0).getTourName());
    tourDao.setUserForTour("good_tour", "admin");
    adminsTours = tourDao.findAllBoughtToursByUsername("admin");
    Assert.assertEquals(2, adminsTours.size());
  }

  @Test
  public void returnTourTest(){
    tourDao.setUserForTour("best_tour", "admin");
    List<Tour> adminsTours = tourDao.findAllBoughtToursByUsername("admin");
    boolean empty = adminsTours.isEmpty();
    Assert.assertEquals(false, empty);
    tourDao.returnTour("best_tour");
    adminsTours = tourDao.findAllBoughtToursByUsername("admin");
    empty = adminsTours.isEmpty();
    Assert.assertEquals(true, empty);
  }
}
