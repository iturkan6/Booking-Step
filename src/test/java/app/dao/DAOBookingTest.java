package app.dao;

import app.entity.Booking;
import app.entity.BookingPredicates;
import app.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DAOBookingTest {
    DAOBooking daoBooking;

    @BeforeEach
    public void initialize() throws IOException {
        this.daoBooking = new DAOBooking();
    }

    @Test
    void get() {
        Booking booking = new Booking(1, 1, null);
        daoBooking.bookings.add(booking);
        Optional<Booking> expected = daoBooking.get(0);
        Optional<Booking> actual = Optional.of(daoBooking.bookings.get(0));
        assertEquals(expected,actual);
    }

    @Test
    void getAll() {
        Booking booking = new Booking(1, 1, null);
        daoBooking.bookings.add(booking);
        List<Booking> book = (ArrayList<Booking>)daoBooking.getAll();
        assertEquals(daoBooking.bookings, book);
    }

    @Test
    void getAllBy(){
        Person mary = new Person("Mary", "Black");
        Person michael = new Person("Michael", "Black");
        Booking booking = new Booking(1, 1, new ArrayList<>(Arrays.asList(mary, michael)));
        daoBooking.bookings.add(booking);
        assertTrue(daoBooking.getAllBy(BookingPredicates.bookingById(1)).contains(booking));
    }

    @Test
    void create() {
        Booking booking = new Booking(4, 1, null);
        daoBooking.create(booking);
        assertEquals(daoBooking.bookings.get(3), booking);

    }

    @Test
    void delete() {
        Booking booking1 = new Booking(4, 1, null);
        Booking booking2 = new Booking(5, 2, null);
        daoBooking.bookings.add(booking1);
        daoBooking.bookings.add(booking2);
        daoBooking.delete(0);
        assertEquals(daoBooking.bookings.get(3), booking2);

    }
}