package com.ibsplc.ndcapp.offer.vo;

import java.io.Serializable;



import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;
import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;

public class RecommendedOfferVO implements Serializable {
     
	private CarDetailsVO carOffer;
	
	private Offer airOffer;
	
	private HotelDetailsVO hotelOffer;
	
	private double totalPrice;
	
	private String currency;

	public CarDetailsVO getCarOffer() {
		return carOffer;
	}

	public void setCarOffer(CarDetailsVO carOffer) {
		this.carOffer = carOffer;
	}

	public Offer getAirOffer() {
		return airOffer;
	}

	public void setAirOffer(Offer airOffer) {
		this.airOffer = airOffer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public HotelDetailsVO getHotelOffer() {
		return hotelOffer;
	}

	public void setHotelOffer(HotelDetailsVO hotelOffer) {
		this.hotelOffer = hotelOffer;
	}

	
    	
	
	
	
	
}
