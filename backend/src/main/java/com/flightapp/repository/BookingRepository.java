package com.flightapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.entity.Booking;
import com.flightapp.model.BookedSeatByFlight;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select b.flightNumber as flightNumber,sum(b.numberOfSeats) as bookedSeat from Booking b group by b.flightNumber")
	List<BookedSeatByFlight> findBookedSeatByFlight();

}
