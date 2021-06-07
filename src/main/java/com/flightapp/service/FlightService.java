package com.flightapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.model.BookedSeatByFlight;
import com.flightapp.model.FlightSearchResponse;
import com.flightapp.model.Search;
import com.flightapp.model.SearchResult;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;

@Service
public class FlightService {
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	BookingRepository bookingRepository;

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

		List<BookedSeatByFlight> bookedSeats = bookingRepository.findBookedSeatByFlight();
		Map<String, Long> bookedSeatMap = mapBookedSeats(bookedSeats);

		System.out.println("Booked FLight" + bookedSeatMap);

		if (null != search.getTrip() && search.getTrip().equalsIgnoreCase("oneway")) {
			List<Flight> response = flightRepository.findByFromCityAndToCityAndDateGreaterThan(search.getFromPlace(),
					search.getToPlace(), search.getStartDate());
			System.out.println("Size of Response" + response.size());
			flightSearchResponse.setOneWayLst(buildSearchResponse(response, bookedSeatMap));
			return flightSearchResponse;

		} else {
			if (null != search.getTrip() && search.getTrip().equalsIgnoreCase("roundtrip")) {
				List<Flight> response = flightRepository.findByFromCityAndToCityAndDateGreaterThan(
						search.getFromPlace(), search.getToPlace(), search.getStartDate());
				System.out.println("Size of Response" + response.size());
				flightSearchResponse.setOneWayLst(buildSearchResponse(response, bookedSeatMap));
				List<Flight> response2 = flightRepository.findByFromCityAndToCityAndDateGreaterThan(search.getToPlace(),
						search.getFromPlace(), search.getEndDate());
				flightSearchResponse.setTwoWayLst(buildSearchResponse(response2, bookedSeatMap));
			}

		}

		return flightSearchResponse;

	}

	private List<SearchResult> buildSearchResponse(List<Flight> response, Map<String, Long> availableSeatMap) {

		List<SearchResult> responseLst = new ArrayList<>();

		for (Flight flight : response) {

			SearchResult searchResult = new SearchResult();

			if (null != availableSeatMap.get(flight.getFlightNo())
					&& (availableSeatMap.get(flight.getFlightNo()) < flight.getTotalNoOfSeats())) {

				searchResult.setFlightName(flight.getFlightName());
				searchResult.setFlightNo(flight.getFlightNo());
				searchResult.setFromCity(flight.getFromCity());
				searchResult.setToCity(flight.getToCity());
				searchResult.setStartDate(flight.getDate());
				searchResult.setAvailableSeat(flight.getTotalNoOfSeats() - availableSeatMap.get(flight.getFlightNo()));
				searchResult.setAvailableStatus("Available");
				responseLst.add(searchResult);

			} else if (null == availableSeatMap.get(flight.getFlightNo())) {
				searchResult.setFlightName(flight.getFlightName());
				searchResult.setFlightNo(flight.getFlightNo());
				searchResult.setFromCity(flight.getFromCity());
				searchResult.setToCity(flight.getToCity());
				searchResult.setStartDate(flight.getDate());
				searchResult.setAvailableSeat(flight.getTotalNoOfSeats());
				searchResult.setAvailableStatus("Available");

				responseLst.add(searchResult);
			} else {
				searchResult.setFlightName(flight.getFlightName());
				searchResult.setFlightNo(flight.getFlightNo());
				searchResult.setFromCity(flight.getFromCity());
				searchResult.setToCity(flight.getToCity());
				searchResult.setStartDate(flight.getDate());
				searchResult.setAvailableSeat(0L);
				searchResult.setAvailableStatus("Fully Booked");
				responseLst.add(searchResult);
			}

		}

		return responseLst;
	}

	public Map<String, Long> mapBookedSeats(List<BookedSeatByFlight> bookedSeats) {
		return bookedSeats.stream()
				.collect(Collectors.toMap(BookedSeatByFlight::getFlightNumber, BookedSeatByFlight::getBookedSeat));

	}

}
