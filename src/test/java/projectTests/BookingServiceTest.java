package projectTests;

import app.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceTest {

  private BookingService service;

  @BeforeEach
  public void initialize() {
    this.service = new BookingService();
  }

  @Test
  public void getNumOfSeatsTest() {
    int expected =0;
    int actual = service.getNumOfSeats(1);
    assertEquals(expected, actual);
  }
}