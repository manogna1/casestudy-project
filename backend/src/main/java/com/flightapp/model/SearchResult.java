package com.flightapp.model;

import java.util.Date;

public class SearchResult {
	private String flightName;
	private String flightNo;
	private String fromCity;
	private String toCity;
	private Date startDate;
	private Long availableSeat;
	private String availableStatus;

	public String getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(String availableStatus) {
		this.availableStatus = availableStatus;
	}

	public Long getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(Long availableSeat) {
		this.availableSeat = availableSeat;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

}
