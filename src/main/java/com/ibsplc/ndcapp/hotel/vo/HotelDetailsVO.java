package com.ibsplc.ndcapp.hotel.vo;

import java.util.List;

public class HotelDetailsVO {
	
	private String hotelName;
	
	private String publicRating;
	
	private String latitude;
	
	private String longitude;
	
	private String starRating;
	
	private List<String> amenities;
	
	private double price;
	
	private String currency;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPublicRating() {
		return publicRating;
	}

	public void setPublicRating(String publicRating) {
		this.publicRating = publicRating;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
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
	
	

}
