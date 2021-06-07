package com.flightapp.controller;

import java.util.List;

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

import com.flightapp.entity.Airline;

import com.flightapp.service.AirlineService;

@RestController
public class AirlineController {
	@Autowired
	AirlineService airlineService;
	
	@GetMapping("/airline")
	public List<Airline> getFlights() {
		
		return airlineService.getAllAirlines();
	}

	@GetMapping(value = "/airline/{id}")
	public Airline getAirline(@PathVariable int id) {
		return airlineService.getAirline(id).orElse(null);
	}

	@PostMapping(value = "/airline")	
	@ResponseStatus(code = HttpStatus.CREATED)
	public int createNewAirline( @RequestBody Airline airline) throws Exception {
		airlineService.createAirline(airline);
		return airline.getId();
	}

	@PutMapping(value = "/airline/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public int updateAirline(@RequestBody Airline airline, @PathVariable int id) {
		airlineService.updateAirline(airline, id);
		return airline.getId();

	}
	@DeleteMapping(value= "/airline/{id}")
	public void deleteAirline(@PathVariable int id)
	{
		airlineService.deleteAirline(id);
	}


}
