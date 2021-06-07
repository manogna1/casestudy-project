package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.entity.Booking;

import com.flightapp.service.BookingService;

@RestController
public class BookingController {
	@Autowired
	BookingService bookingService;

	@PostMapping("/flight/booking")
	public int bookflight(@RequestBody Booking booking) {
		return bookingService.newBooking(booking);
	}

	@GetMapping("/flight/booking/{id}")
	public Booking getBookingdetails(@PathVariable int id) {
		return bookingService.getBookingDetails(id).orElse(null);
	}

	@PutMapping("/flight/booking/{id}")

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public int updateBooking(@RequestBody Booking booking, @PathVariable int id) {
		bookingService.updateBooking(booking, id);
		return booking.getId();
	}

	@DeleteMapping("flight/booking/cancel")
	public void cancelBooking(int id) {
		bookingService.deleteBooking(id);
	}

}
