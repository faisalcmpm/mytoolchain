package com.ibsplc.ndcapp.air.model;

import java.util.Calendar;
import java.util.List;

public class AirShoppingResponse {
	

	private List<Offer> offers;
	private String errorCode;
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public static class Offer{
		private String offerId;
		private double price;
		private String currency;
		private List<FlightSegment> flights;
		private String origin;
		private String destination;
		private String depDate;
		private String depTime;
		private String arrDate;
		private String arrTime;
		private String journeyTime;
		
		public String getOfferId() {
			return offerId;
		}
		public void setOfferId(String offerId) {
			this.offerId = offerId;
		}
		public String getJourneyTime() {
			return journeyTime;
		}
		public void setJourneyTime(String journeyTime) {
			this.journeyTime = journeyTime;
		}
		public String getDepDate() {
			return depDate;
		}
		public void setDepDate(String depDate) {
			this.depDate = depDate;
		}
		public String getDepTime() {
			return depTime;
		}
		public void setDepTime(String depTime) {
			this.depTime = depTime;
		}
		public String getArrDate() {
			return arrDate;
		}
		public void setArrDate(String arrDate) {
			this.arrDate = arrDate;
		}
		public String getArrTime() {
			return arrTime;
		}
		public void setArrTime(String arrTime) {
			this.arrTime = arrTime;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public List<FlightSegment> getFlights() {
			return flights;
		}
		public void setFlights(List<FlightSegment> flights) {
			this.flights = flights;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		
	}
	
	public static class FlightSegment {
		private String carrierCode;
		private String flightNumber;
		private String bdAirportCode;
		private String offAirportCode;
		private String depDate;
		private String arrDate;
		private String depTime;
		private String arrTime;
		private String jourenyTime;
		public String getJourenyTime() {
			return jourenyTime;
		}
		public void setJourenyTime(String jourenyTime) {
			this.jourenyTime = jourenyTime;
		}
		public String getCarrierCode() {
			return carrierCode;
		}
		public void setCarrierCode(String carrierCode) {
			this.carrierCode = carrierCode;
		}
		public String getDepDate() {
			return depDate;
		}
		public void setDepDate(String depDate) {
			this.depDate = depDate;
		}
		public String getArrDate() {
			return arrDate;
		}
		public void setArrDate(String arrDate) {
			this.arrDate = arrDate;
		}
		public String getDepTime() {
			return depTime;
		}
		public void setDepTime(String depTime) {
			this.depTime = depTime;
		}
		public String getArrTime() {
			return arrTime;
		}
		public void setArrTime(String arrTime) {
			this.arrTime = arrTime;
		}
		public String getFlightNumber() {
			return flightNumber;
		}
		public void setFlightNumber(String flightNumber) {
			this.flightNumber = flightNumber;
		}
		public String getBdAirportCode() {
			return bdAirportCode;
		}
		public void setBdAirportCode(String bdAirportCode) {
			this.bdAirportCode = bdAirportCode;
		}
		public String getOffAirportCode() {
			return offAirportCode;
		}
		public void setOffAirportCode(String offAirportCode) {
			this.offAirportCode = offAirportCode;
		}
		
	}
}
