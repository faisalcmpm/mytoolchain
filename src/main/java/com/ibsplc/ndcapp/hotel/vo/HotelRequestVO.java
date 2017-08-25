package com.ibsplc.ndcapp.hotel.vo;

import java.util.Calendar;

public class HotelRequestVO {
   
	private Calendar checkinDate;
	
	private Calendar checkoutDate;
	
	private int numberOfRooms;
	
	private int numberOfGuests;
	
	private String country;
	
	private String requestCurrency;
	
	private String locale;
	
	private String stayLatitute;
	
	private String stayLongitude;

	public Calendar getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Calendar checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Calendar getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Calendar checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRequestCurrency() {
		return requestCurrency;
	}

	public void setRequestCurrency(String requestCurrency) {
		this.requestCurrency = requestCurrency;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getStayLatitute() {
		return stayLatitute;
	}

	public void setStayLatitute(String stayLatitute) {
		this.stayLatitute = stayLatitute;
	}

	public String getStayLongitude() {
		return stayLongitude;
	}

	public void setStayLongitude(String stayLongitude) {
		this.stayLongitude = stayLongitude;
	}

	@Override
	public String toString() {
		return "HotelRequestVO [checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", numberOfRooms=" + numberOfRooms
				+ ", numberOfGuests=" + numberOfGuests + ", country=" + country
				+ ", requestCurrency=" + requestCurrency + ", locale=" + locale
				+ ", stayLatitute=" + stayLatitute + ", stayLongitude="
				+ stayLongitude + "]";
	}
	
	
	
	
}
