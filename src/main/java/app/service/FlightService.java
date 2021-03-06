package app.service;


import app.dao.DAOFlights;
import app.entity.Flight;
import app.entity.FlightPredicates;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class FlightService {

  DAOFlights flight = new DAOFlights();

  public FlightService() throws IOException {
  }

  public ArrayList<Flight> getAllFlights() {
    return (ArrayList<Flight>) flight.getAll();
  }


  public ArrayList<Flight> getFlights24H() {
    return (ArrayList<Flight>) flight.getAllBy(FlightPredicates.flightIn24H());
  }

  public Flight getFlightBy(Predicate<Flight> p) {
    return getAllFlights().stream().filter(p).findFirst()
            .orElseThrow(()-> new IllegalArgumentException("Flights with such parameters are not found"));
  }

  public Collection<Flight> getFlightsBy(String dest, LocalDate date, int seats){
    return flight.getAllBy(FlightPredicates.flightByInfo(dest, date, seats));
  }

  public void occupySeats(int seats, int id){
    getFlightBy(FlightPredicates.flightsById(id)).occupySeats(seats);
  }

  public void save() throws IOException {
    flight.write();
  }
}
