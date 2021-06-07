package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;

import com.flightapp.model.FlightSearchResponse;
import com.flightapp.model.Search;
import com.flightapp.model.SearchResult;
import com.flightapp.repository.FlightRepository;

@Service
public class FlightService {
	@Autowired
	FlightRepository flightRepository;

	public String create(Flight flight) {
		flightRepository.save(flight);
		return flight.getFlightNo();
	}

	public Optional<Flight> getFlight(int id) {
		return flightRepository.findById(id);
	}

	public String updateFlight(Flight flight, int id) {

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

	public List<Flight> getAllFlights() {
		return flightRepository.findAll();

	}

	public FlightSearchResponse getSearchFlight(Search search) {

		FlightSearchResponse flightSearchResponse = new FlightSearchResponse();

		if (null != search.getTrip() && search.getTrip().equalsIgnoreCase("oneway")) {
			List<Flight> response = flightRepository.findByFromCityAndToCityAndDateGreaterThan(search.getFromPlace(),
					search.getToPlace(), search.getStartDate());
			System.out.println("Size of Response" + response.size());
			flightSearchResponse.setOneWayLst(buildSearchResponse(response));
			return flightSearchResponse;

		} else {
			if (null != search.getTrip() && search.getTrip().equalsIgnoreCase("roundtrip")) {
				List<Flight> response = flightRepository.findByFromCityAndToCityAndDateGreaterThan(
						search.getFromPlace(), search.getToPlace(), search.getStartDate());
				System.out.println("Size of Response" + response.size());
				flightSearchResponse.setOneWayLst(buildSearchResponse(response));
				List<Flight> response2 = flightRepository.findByFromCityAndToCityAndDateGreaterThan(search.getToPlace(),
						search.getFromPlace(), search.getEndDate());
				flightSearchResponse.setTwoWayLst(buildSearchResponse(response2));
			}

		}

		return flightSearchResponse;

	}

	private List<SearchResult> buildSearchResponse(List<Flight> response) {

		List<SearchResult> responseLst = new ArrayList<>();

		for (Flight flight : response) {
			SearchResult searchResult = new SearchResult();

			searchResult.setFlightName(flight.getFlightName());
			searchResult.setFlightNo(flight.getFlightNo());
			searchResult.setFromCity(flight.getFromCity());
			searchResult.setToCity(flight.getToCity());
			searchResult.setStartDate(flight.getDate());
			responseLst.add(searchResult);

		}

		return responseLst;
	}

}
