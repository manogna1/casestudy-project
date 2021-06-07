package com.flightapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Booking;
import com.flightapp.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository bookingRepository;

	public int newBooking(Booking booking) {

		bookingRepository.save(booking);

		return booking.getId();

	}

	public Optional<Booking> getBookingDetails(int id) {
		return bookingRepository.findById(id);
	}

	public int updateBooking(Booking booking, int id) {

		Booking res = bookingRepository.findById(id).get();
		res.setFirstName(booking.getFirstName());
		res.setLastName(booking.getLastName());
		res.setNumberOfSeats(booking.getNumberOfSeats());
		res.setMealPreference(booking.getMealPreference());
		return res.getId();
	}

	public void deleteBooking(int id) {
		bookingRepository.deleteById(id);
	}

}
