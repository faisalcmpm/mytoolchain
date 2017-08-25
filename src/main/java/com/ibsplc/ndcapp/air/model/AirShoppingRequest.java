package com.ibsplc.ndcapp.air.model;

import java.util.Calendar;

public class AirShoppingRequest {
	public Calendar getOnwardDate() {
		return onwardDate;
	}

	public void setOnwardDate(Calendar onwardDate) {
		this.onwardDate = onwardDate;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate) {
		this.returnDate = returnDate;
	}

	private String locale;
	private int adultCount;
	private int childCount;
	private int infantCount;
	private String originAirport;
	private String destinationAirport;
	private Calendar onwardDate;
	private Calendar returnDate;
	private int daysOut;

	public String getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public int getInfantCount() {
		return infantCount;
	}

	public void setInfantCount(int infantCount) {
		this.infantCount = infantCount;
	}

	public int getDaysOut() {
		return daysOut;
	}

	public void setDaysOut(int daysOut) {
		this.daysOut = daysOut;
	}

	

}
