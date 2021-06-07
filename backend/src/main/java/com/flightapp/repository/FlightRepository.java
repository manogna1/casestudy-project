package com.flightapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flightapp.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	public List<Flight> findByFromCityAndToCityAndDateGreaterThan(String fromCity, String toCity, Date Date);
	
}
