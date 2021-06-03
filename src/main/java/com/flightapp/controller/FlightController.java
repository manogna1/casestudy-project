package com.flightapp.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.entity.Flight;
import com.flightapp.service.FlightService;




@RestController
public class FlightController {
	@Autowired
	FlightService flightService;

	@GetMapping("/flight")
	public List<Flight> getFlights() {
		
		return flightService.getAllFlights();
	}

	@GetMapping(value = "/flight/{id}")
	public Flight getFlight(@PathVariable int id) {
		return flightService.getFlight(id).orElse(null);
	}

	@PostMapping(value = "/flight")
	
	@ResponseStatus(code = HttpStatus.CREATED)
	public int createNewFlight( @RequestBody Flight flight) throws Exception {
		flightService.create(flight);
		return flight.getFlightNo();
	}

	@PutMapping(value = "/flight/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public int updateBug(@RequestBody Flight flight, @PathVariable int id) {
		flightService.updateFlight(flight, id);
		return flight.getFlightNo();

	}

	
}



