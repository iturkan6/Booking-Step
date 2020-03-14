package app;


import app.controller.BookingController;
import app.controller.FlightController;
import app.dao.DAOBooking;
import app.io.ConsoleMain;
import app.io.Generator;
import app.service.BookingService;

public class MainApp {
  public static void main(String[] args) {

    Generator g = new Generator();
    g.generator();

    ConsoleMain console = new ConsoleMain();
    DAOBooking daoBooking = new DAOBooking();
//    DAOBooking daoBooking = new DAOBooking();
//    DAOBooking daoBooking = new DAOBooking();
//    DAOBooking daoBooking = new DAOBooking();
    BookingService service = new BookingService(daoBooking);
//    Service service = new Service(daoBooking);
//    Service service = new Service(daoBooking);
//    Service service = new Service(daoBooking);
//    Service service = new Service(daoBooking);
    BookingController bookingController = new BookingController(console, service);
//    Controller controller = new Controller(service);
//    Controller controller = new Controller(service);
//    Controller controller = new Controller(service);
    FlightController flightController = new FlightController();

    while (true) {
      console.displayMainMenu();
      String input = console.readLn();
      switch (input) {
        case "1": flightController.show24H(); break;
        case "2": flightController.showInfo(Integer.parseInt(console.readLn())); break;
        case "3": {
          console.print("Enter destination: ");
          String dest = console.readLn();
          console.print("Enter desired date: ");
          String date = console.readLn();
          console.print("Enter number of seats you wish to book:");
          int seats = Integer.parseInt(console.readLn());
          flightController.showFlightsByInfo(dest,date,seats);
          console.print("Enter id of flight to book or '0' to return to main menu: ");
          int id = Integer.parseInt(console.readLn());
          if(id!=0) {
            bookingController.book(id, seats);
            flightController.makeReservation(seats, id);
          }
          break;
        }
        case "4": {
          console.printLn("Enter the id of booking to be cancelled: ");
          int id = Integer.parseInt(console.readLn());
          bookingController.cancel(id);
          flightController.removeReservation(bookingController.getNumOfSeats(id),bookingController.getFlightIdInBooking(id));
          break;
        }

        case "5": bookingController.show(); break;
        case "6":
        default:  break;
      }
    }
  }
}
