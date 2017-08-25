package com.ibsplc.ndcapp.carrental.vo;

public class CarDetailsVO {

	private double carRentalPrice;
	private String currency;
	private int numberOfseats;
	private boolean isAirConditioned;
	private String vehicleMake;
	private String imageUrl;
	private double additionalDriverPrice;
	private double totalCost;
	private int numberOfBags;
	private String vehicleType;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	public boolean isAirConditioned() {
		return isAirConditioned;
	}
	public void setAirConditioned(boolean isAirConditioned) {
		this.isAirConditioned = isAirConditioned;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getAdditionalDriverPrice() {
		return additionalDriverPrice;
	}
	public void setAdditionalDriverPrice(double additionalDriverPrice) {
		this.additionalDriverPrice = additionalDriverPrice;
	}
	public double getCarRentalPrice() {
		return carRentalPrice;
	}
	public void setCarRentalPrice(double carRentalPrice) {
		this.carRentalPrice = carRentalPrice;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getNumberOfseats() {
		return numberOfseats;
	}
	public void setNumberOfseats(int numberOfseats) {
		this.numberOfseats = numberOfseats;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Override
	public String toString() {
		return "CarDetailsVO [carRentalPrice=" + carRentalPrice + ", currency="
				+ currency + ", numberOfseats=" + numberOfseats
				+ ", isAirConditioned=" + isAirConditioned + ", vehicleMake="
				+ vehicleMake + ", imageUrl=" + imageUrl
				+ ", additionalDriverPrice=" + additionalDriverPrice
				+ ", totalCost=" + totalCost + ", numberOfBags=" + numberOfBags
				+ ", vehicleType=" + vehicleType + "]";
	}
	
	
	
	
	
	

}
