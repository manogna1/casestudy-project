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

import com.flightapp.entity.Airline;
import com.flightapp.entity.Flight;
import com.flightapp.model.FlightSearchResponse;
import com.flightapp.model.Search;
import com.flightapp.model.SearchResult;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.service.FlightService;

@RestController
public class FlightController {
	@Autowired
	private FlightService flightService;

	@Autowired
	private AirlineRepository airlineRepository;

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
	public String createNewFlight(@RequestBody Flight flight) throws Exception {
		Airline airline = airlineRepository.findById(flight.getAirlineId()).get();
		flight.setAirline(airline);
		flightService.create(flight);
		return flight.getFlightNo();
	}

	@PutMapping(value = "/flight/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String updateFlight(@RequestBody Flight flight, @PathVariable int id) {
		flightService.updateFlight(flight, id);
		return flight.getFlightNo();

	}

	@GetMapping("/flight/search")
	FlightSearchResponse getSearchFlights(@RequestBody Search search) {
		return flightService.getSearchFlight(search);
	}

}
