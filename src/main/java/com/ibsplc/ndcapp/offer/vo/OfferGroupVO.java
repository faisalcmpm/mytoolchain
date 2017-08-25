package com.ibsplc.ndcapp.offer.vo;

import java.io.Serializable;
import java.util.List;




import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;
import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;

public class OfferGroupVO implements Serializable {
	
	private List<CarDetailsVO> carOffers;
	
	private List<Offer> airOffers;
	
	private List<HotelDetailsVO> hotelOffers;
	
	private RecommendedOfferVO recommendedOfferVO;
	private String errorCode;
	private String errorMessage;
	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	private String currentLocation;
	private String destinationLocation;
	private List<String> topAirlineCodes;

	public List<CarDetailsVO> getCarOffers() {
		return carOffers;
	}

	public void setCarOffers(List<CarDetailsVO> carOffers) {
		this.carOffers = carOffers;
	}
	
    
	public List<Offer> getAirOffers() {
		return airOffers;
	}

	public void setAirOffers(List<Offer> airOffers) {
		this.airOffers = airOffers;
	}

	public RecommendedOfferVO getRecommendedOfferVO() {
		return recommendedOfferVO;
	}

	public void setRecommendedOfferVO(RecommendedOfferVO recommendedOfferVO) {
		this.recommendedOfferVO = recommendedOfferVO;
	}

	public List<HotelDetailsVO> getHotelOffers() {
		return hotelOffers;
	}

	public void setHotelOffers(List<HotelDetailsVO> hotelOffers) {
		this.hotelOffers = hotelOffers;
	}

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

	public List<String> getTopAirlineCodes() {
		return topAirlineCodes;
	}

	public void setTopAirlineCodes(List<String> topAirlineCodes) {
		this.topAirlineCodes = topAirlineCodes;
	}

}
