package com.ibsplc.ndcapp.carrental.vo;

import java.util.Calendar;

public class CarDetailsRequestVO {
	
	private String country;
	
	private String requestCurrency;
	
	private String locale;
	
	private String pickupLocationLatitute;
	
	private String pickupLocationLongitute;
	
	private String dropOffLocationLatitute;
	
	private String dropOffLocationLongitute;
	
	
	private Calendar pickupTime;
	
	private Calendar dropOffTime;
	
	private String driverAge;
	
	private String userIp;

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

	public String getPickupLocationLatitute() {
		return pickupLocationLatitute;
	}

	public void setPickupLocationLatitute(String pickupLocationLatitute) {
		this.pickupLocationLatitute = pickupLocationLatitute;
	}

	public String getPickupLocationLongitute() {
		return pickupLocationLongitute;
	}

	public void setPickupLocationLongitute(String pickupLocationLongitute) {
		this.pickupLocationLongitute = pickupLocationLongitute;
	}

	public String getDropOffLocationLatitute() {
		return dropOffLocationLatitute;
	}

	public void setDropOffLocationLatitute(String dropOffLocationLatitute) {
		this.dropOffLocationLatitute = dropOffLocationLatitute;
	}

	public String getDropOffLocationLongitute() {
		return dropOffLocationLongitute;
	}

	public void setDropOffLocationLongitute(String dropOffLocationLongitute) {
		this.dropOffLocationLongitute = dropOffLocationLongitute;
	}

	public Calendar getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Calendar pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Calendar getDropOffTime() {
		return dropOffTime;
	}

	public void setDropOffTime(Calendar dropOffTime) {
		this.dropOffTime = dropOffTime;
	}

	

	public String getDriverAge() {
		return driverAge;
	}

	public void setDriverAge(String driverAge) {
		this.driverAge = driverAge;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	

}
