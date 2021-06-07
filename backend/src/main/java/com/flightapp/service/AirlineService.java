package com.flightapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Airline;

import com.flightapp.repository.AirlineRepository;

@Service
public class AirlineService {
	@Autowired
	AirlineRepository airlineRepository;
	
	public int createAirline(Airline airline)  {

		airlineRepository.save(airline);
		return airline.getId();

	}
	public Optional<Airline> getAirline(int id)
	{
	return	airlineRepository.findById(id);
	}
	public int updateAirline(Airline airline,int id)
	{
		Airline res = airlineRepository.findById(id).get();
		res.setName(airline.getName());
		res.setAddress(airline.getAddress());
		res.setEmail(airline.getEmail());
		res.setContactNumber(airline.getContactNumber());
		
		airlineRepository.save(res);
		return res.getId();	
	}
	public List<Airline> getAllAirlines()
	{
		return airlineRepository.findAll();
		
	}
	public void deleteAirline(int id)
	{
		airlineRepository.deleteById(id);
		
	}


}
