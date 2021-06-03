package com.flightapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;


@Service
public class FlightService {
	@Autowired
	FlightRepository flightRepository;

	public int create(Flight flight)  {

		flightRepository.save(flight);
		return flight.getFlightNo();

	}
	public Optional<Flight> getFlight(int id)
	{
		return flightRepository.findById(id);
	}
	public int updateFlight(Flight flight,int id)
	{
		
		Flight res = flightRepository.findById(id).get();
		res.setAirportName(flight.getAirportName());
		res.setDate(flight.getDate());
		res.setFlightName(flight.getFlightName());
		res.setFromCity(flight.getFromCity());
		res.setToCity(flight.getToCity());
		res.setTicketPrice(flight.getTicketPrice());
	
		flightRepository.save(res);
		return res.getFlightNo();
	}
	public List<Flight> getAllFlights()
	{
		return flightRepository.findAll();
		
	}

}
