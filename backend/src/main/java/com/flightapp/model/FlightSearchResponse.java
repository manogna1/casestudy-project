package com.flightapp.model;

import java.util.List;

public class FlightSearchResponse {
	List<SearchResult> oneWayLst;
	List<SearchResult> twoWayLst;

	public List<SearchResult> getOneWayLst() {
		return oneWayLst;
	}

	public void setOneWayLst(List<SearchResult> oneWayLst) {
		this.oneWayLst = oneWayLst;
	}

	public List<SearchResult> getTwoWayLst() {
		return twoWayLst;
	}

	public void setTwoWayLst(List<SearchResult> twoWayLst) {
		this.twoWayLst = twoWayLst;
	}

}
