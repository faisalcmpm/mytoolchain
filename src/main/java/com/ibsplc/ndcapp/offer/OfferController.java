package com.ibsplc.ndcapp.offer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibsplc.ndcapp.air.connector.NdcConnector;
import com.ibsplc.ndcapp.air.connector.impl.XQNdcConnector;
import com.ibsplc.ndcapp.air.model.AirShoppingRequest;
import com.ibsplc.ndcapp.air.model.AirShoppingResponse;
import com.ibsplc.ndcapp.air.model.AirShoppingResponse.Offer;
import com.ibsplc.ndcapp.airport.AirportDetails;
import com.ibsplc.ndcapp.airport.AirportLocationController;
import com.ibsplc.ndcapp.carrental.CarRentalConnector;
import com.ibsplc.ndcapp.carrental.CarRentalConstants;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsRequestVO;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;
import com.ibsplc.ndcapp.framework.exception.GenericException;
import com.ibsplc.ndcapp.hotel.HotelConnector;
import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;
import com.ibsplc.ndcapp.hotel.vo.HotelRequestVO;
import com.ibsplc.ndcapp.offer.util.AirOfferComparator;
import com.ibsplc.ndcapp.offer.vo.OfferGroupVO;
import com.ibsplc.ndcapp.offer.vo.RecommendedOfferVO;
import com.ibsplc.ndcapp.util.ArithmeticUtil;
import com.ibsplc.ndcapp.util.CalendarUtil;
import com.ibsplc.ndcapp.util.CountryUtil;
import com.ibsplc.ndcapp.util.PropertiesHolder;
import com.ibsplc.ndcapp.util.flickr.FlickrConnector;
import com.ibsplc.ndcapp.util.flickr.json.Location;
import com.ibsplc.ndcapp.util.googleVision.GoogleVisionInterface;
import com.ibsplc.ndcapp.util.googleVision.vo.LocationDetails;


@Controller
public class OfferController {

	final Logger l = Logger.getLogger("OfferController.class");

	@RequestMapping(value="/getOffer.htm")
	@ResponseBody
	public OfferGroupVO getOffer(@RequestParam("imageUrl") String url,@RequestParam("originLat") String orgLatitude,@RequestParam("originLon") String orgLongitude) throws Exception{
		l.debug("Start Get Offer");
		OfferGroupVO offerGroupVO = new OfferGroupVO();
//		
//		System.out.println("Start Test testXQNdc");
//		Currency currency =   Currency.getInstance("EUR");		
//		AirShoppingRequest request = new AirShoppingRequest();
//		request.setOriginAirport("FRA");
//		request.setDestinationAirport("ADB");
//		Calendar depDate = new GregorianCalendar(2017, Calendar.JUNE, 23); 
//		request.setOnwardDate(depDate);
//		request.setAdultCount(1);
//		NdcConnector connector = new XQNdcConnector();
//		AirShoppingResponse response = connector.shopAir(request);
//		System.out.println(response);
//		
//		System.out.println("End Test testXQNdc");
		
		try{
			AirportDetails originDetails = getNearestOrigin(orgLatitude,orgLongitude);
			Location destinationLocation = getFlickrLocationData(url);
			AirportDetails destinationDetails = null;
			String destinationString = null;
			if(destinationLocation!=null){
				destinationDetails = getNearestDestinationFromImage(url,destinationLocation);
				destinationString = destinationLocation.getLocality().get_content() +", "+destinationLocation.getCountry().get_content();
				offerGroupVO.setDestinationLocation(destinationString);
			}else{
				LocationDetails details = GoogleVisionInterface.postVisionRequest(url);
				if(details!=null){
					destinationString = details.getAddress();
					offerGroupVO.setDestinationLocation(destinationString);
					destinationLocation = new Location();
					destinationLocation.setLatitude(details.getLat());
					destinationLocation.setLongitude(details.getLng());
					destinationDetails = getNearestOrigin(details.getLat(),details.getLng());
				} else {
					throw new GenericException(GenericException.UNABLE_TO_LOCATE_THE_IMAGE,
							PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_LOCATE_THE_IMAGE));
				}

			}
			if(originDetails!=null&&destinationDetails!=null) {
				int maxRetryCount = 10;
				int count = 0;
				int addendumDays = 8;
				
				AirShoppingResponse airShoppingResponse = getAirshoppingResponse(originDetails, destinationDetails, null,addendumDays);
				while((airShoppingResponse.getOffers() == null || airShoppingResponse.getOffers().isEmpty())&& count < maxRetryCount) {
					count++;
					addendumDays++;
					airShoppingResponse = getAirshoppingResponse(originDetails, destinationDetails, null,addendumDays);					
				}
				if(airShoppingResponse == null || airShoppingResponse.getOffers() == null || airShoppingResponse.getOffers().isEmpty()) {
					throw new GenericException(GenericException.NO_AIR_OFFERS_AVAILABLE, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.NO_AIR_OFFERS_AVAILABLE));
				}
				Offer reccommendedOffer = findReccommendedAirOffer(airShoppingResponse);
				Calendar reccommendedDate = CalendarUtil.toCalendar(reccommendedOffer.getDepDate(), "yyyy-MM-dd");
				count = 0;
				if ((reccommendedOffer.getFlights() == null || reccommendedOffer.getFlights().isEmpty())&& count < maxRetryCount) {
					count++;
					airShoppingResponse = getAirshoppingResponse(originDetails, destinationDetails, reccommendedDate,0);
					while (airShoppingResponse.getErrorCode() != null) {
						reccommendedDate.add(Calendar.DAY_OF_MONTH, 1);
						airShoppingResponse = getAirshoppingResponse(originDetails, destinationDetails, reccommendedDate,0);
					}
				}
				if(airShoppingResponse == null || airShoppingResponse.getOffers() == null || airShoppingResponse.getOffers().isEmpty()) {
					throw new GenericException(GenericException.NO_AIR_OFFERS_AVAILABLE, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.NO_AIR_OFFERS_AVAILABLE));
				}
				Calendar reccommendedArrDate = CalendarUtil.toCalendar(airShoppingResponse.getOffers().get(0).getArrDate(), "yyyy-MM-dd");
				reccommendedArrDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(airShoppingResponse.getOffers().get(0).getArrTime().split(":")[0]));
				reccommendedArrDate.set(Calendar.MINUTE, Integer.parseInt(airShoppingResponse.getOffers().get(0).getArrTime().split(":")[1]));
				List<CarDetailsVO> carDetailsVOs = getCarRentalDetails (originDetails, destinationDetails, destinationLocation, reccommendedArrDate);
				List<HotelDetailsVO> hotelDetailsVOs = getHotelDetails(originDetails,destinationDetails,destinationLocation, reccommendedArrDate);
				offerGroupVO = buildBestOffer(airShoppingResponse.getOffers(), carDetailsVOs,hotelDetailsVOs);
				offerGroupVO.setDestinationLocation(destinationString);
//				offerGroupVO.setCurrentLocation(currentLocation);
				
//				AppInTheAirConnector appInTheAirConnector = new AppInTheAirConnector();
//				Airlines[] airlines = appInTheAirConnector.getUserDetails();
//				if(airlines != null) {
//					List<Airlines> airlinesList = new ArrayList(Arrays.asList(airlines));
//					Collections.sort(airlinesList, new Comparator<Airlines>() {
//						@Override
//						public int compare(Airlines o1, Airlines o2) {
//							if (Integer.parseInt(o1.getCount()) > Integer.parseInt(o2.getCount())) {
//								return 1;
//							} else if (Integer.parseInt(o1.getCount()) < Integer.parseInt(o2.getCount())) {
//								return -1;
//							} else {
//								return 0;
//							}
//						}
//					});
//					if (airlinesList.size() > 5) {
//						airlinesList = airlinesList.subList(0, 5);
//					}
//					List<String> topAirlineCodes = new ArrayList<String>();
//					for(Airlines topAirlines : airlinesList) {
//						topAirlineCodes.add(topAirlines.getCode());
//					}
//					offerGroupVO.setTopAirlineCodes(topAirlineCodes);
//				}
				return offerGroupVO; 
			} else {
				return null;
			}
		} catch(GenericException e) {
//			offerGroupVO = new OfferGroupVO();
			offerGroupVO.setErrorCode(e.getErrCode());
			offerGroupVO.setErrorMessage(e.getErrMsg());
		} catch(Exception e) {
//			offerGroupVO = new OfferGroupVO();
			offerGroupVO.setErrorCode(""+-1);
			offerGroupVO.setErrorMessage("Sorry, Please try later");
		} finally{
			l.debug("End Get Offer");		
		}
		return offerGroupVO;
	}


	private Offer findReccommendedAirOffer(
			AirShoppingResponse airShoppingResponse) {
		List<Offer> offers = airShoppingResponse.getOffers();
		Collections.sort(offers, new AirOfferComparator());
		Offer offer = offers.get(0);
		return offer;
	}


	private List<HotelDetailsVO> getHotelDetails(AirportDetails originAirportDetails,AirportDetails destinationAirportDetails,Location destinationLocation, Calendar reccommendedArrDate) {
		List<HotelDetailsVO> hotelDetailsVOs = null;
		try{			
			HotelRequestVO hotelRequestVO = new HotelRequestVO();

			hotelRequestVO.setCheckinDate(reccommendedArrDate);
			Calendar checkoutDate = new GregorianCalendar(reccommendedArrDate.get(Calendar.YEAR), reccommendedArrDate.get(Calendar.MONTH), reccommendedArrDate.get(Calendar.DAY_OF_MONTH)+1,
					reccommendedArrDate.get(Calendar.HOUR_OF_DAY), reccommendedArrDate.get(Calendar.MINUTE), reccommendedArrDate.get(Calendar.SECOND));
			hotelRequestVO.setCheckoutDate(checkoutDate);
			hotelRequestVO.setCountry(CountryUtil.getCountryCode(destinationAirportDetails.getAirports()[0].getCountry()));
			hotelRequestVO.setLocale("en_"+CountryUtil.getCountryCode(originAirportDetails.getAirports()[0].getCountry()));
			hotelRequestVO.setNumberOfGuests(1);
			hotelRequestVO.setNumberOfRooms(1);
			hotelRequestVO.setRequestCurrency(PropertiesHolder.getProperty(CarRentalConstants.COUNTRY_CURRENCY_PROPERTY, CountryUtil.getCountryCode(originAirportDetails.getAirports()[0].getCountry())));
			hotelRequestVO.setStayLatitute(destinationLocation.getLatitude()); 
			hotelRequestVO.setStayLongitude(destinationLocation.getLongitude());
			hotelDetailsVOs = new HotelConnector().getLowestHotelPrices(hotelRequestVO);
		}catch (Exception e) {
			//throw new GenericException(GenericException.UNABLE_TO_FIND_HOTELS, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_FIND_HOTELS));
		}
		return hotelDetailsVOs;
	}


	private List<CarDetailsVO> getCarRentalDetails(AirportDetails originAirportDetails, AirportDetails destinationAirportDetails, Location destinationLocation, Calendar reccommendedDate) {
		List<CarDetailsVO> carDetails = null;
		try {
			CarDetailsRequestVO requestVO = new CarDetailsRequestVO();
			requestVO.setCountry(CountryUtil.getCountryCode(destinationAirportDetails.getAirports()[0].getCountry()));
			requestVO.setDriverAge(PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.CAR_DRIVER_AGE));
			requestVO.setDropOffLocationLatitute(destinationLocation.getLatitude());
			requestVO.setDropOffLocationLongitute(destinationLocation.getLongitude());

			requestVO.setLocale("en_"+CountryUtil.getCountryCode(originAirportDetails.getAirports()[0].getCountry()));
			requestVO.setPickupLocationLatitute(destinationAirportDetails.getAirports()[0].getLat());
			requestVO.setPickupLocationLongitute(destinationAirportDetails.getAirports()[0].getLng());
			requestVO.setRequestCurrency(PropertiesHolder.getProperty(CarRentalConstants.COUNTRY_CURRENCY_PROPERTY, CountryUtil.getCountryCode(originAirportDetails.getAirports()[0].getCountry())));
			//			if (reccommendedDate == null) {
			//				Calendar pickupTime = Calendar.getInstance();
			//				pickupTime.add(Calendar.DAY_OF_MONTH, 4);
			//				requestVO.setPickupTime(pickupTime);
			//				Calendar dropOffTime = Calendar.getInstance();
			//				dropOffTime.add(Calendar.DAY_OF_MONTH, 4);
			//				dropOffTime.add(Calendar.HOUR_OF_DAY, 2);
			//				requestVO.setDropOffTime(dropOffTime);
			//			} else {
			requestVO.setPickupTime(reccommendedDate);
			Calendar dropOffTime = new GregorianCalendar(reccommendedDate.get(Calendar.YEAR), reccommendedDate.get(Calendar.MONTH), reccommendedDate.get(Calendar.DAY_OF_MONTH),
					reccommendedDate.get(Calendar.HOUR_OF_DAY)+2, reccommendedDate.get(Calendar.MINUTE), reccommendedDate.get(Calendar.SECOND));
			//				dropOffTime.add(Calendar.HOUR_OF_DAY, 2);
			requestVO.setDropOffTime(dropOffTime);
			//			}
			CarRentalConnector carRentalConnector = new CarRentalConnector();
			int counter = 1;
			carDetails =  carRentalConnector.getLowestCarPrice(requestVO);
			int maxRetryCount = Integer.parseInt(PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.MAX_RETRY_COUNT));
			while (carDetails == null && counter < maxRetryCount) {
				carDetails = carRentalConnector.getLowestCarPrice(requestVO);
				counter++;
			}
		} catch (Exception e) {
			//throw new GenericException(GenericException.UNABLE_FIND_CABS, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_FIND_CABS));
		}
		return carDetails;
	}


	private AirShoppingResponse getAirshoppingResponse(
			AirportDetails originDetails, AirportDetails destinationDetails, Calendar depDate, int addendumDays) {
		AirShoppingRequest request = new AirShoppingRequest();
		request.setAdultCount(1);
		request.setOriginAirport(originDetails.getAirports()[0].getIatacode());
		request.setDestinationAirport(destinationDetails.getAirports()[0].getIatacode());
		//TODO		request.set
		request.setLocale(CountryUtil.getCountryCode(originDetails.getAirports()[0].getCountry()));
		if (depDate == null) {
			Calendar onwardDate = new GregorianCalendar();
			onwardDate.add(Calendar.DAY_OF_MONTH, addendumDays); 
			request.setOnwardDate(onwardDate);
			request.setDaysOut(-1);
		} else {
			request.setOnwardDate(depDate);
		}
		NdcConnector ndcConnector = new XQNdcConnector();
		return ndcConnector.shopAir(request);
	}


	private AirportDetails getNearestDestinationFromImage(String url, Location location) {
		l.debug("Start get Nearest destination from image");
		try {
			if(location!=null) {
				return AirportLocationController.getNearestAirport(location.getLatitude(),location.getLongitude());	
			} else {
				return null;
			}				
		} catch (GenericException e) {
			if (e.getErrCode().equals(GenericException.UNABLE_TO_LOCATE)) {
				throw new GenericException(GenericException.UNABLE_TO_LOCATE_DEST_AIRPORT, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_LOCATE_DEST_AIRPORT));
			}
		}finally{
			l.debug("End get Nearest destination from image");
		}
		return null;
	}

	private AirportDetails getNearestOrigin(String latitude, String longitude) {
		l.debug("Start get Nearest Origin Airport");
		try{
			return AirportLocationController.getNearestAirport(latitude,longitude);
		}finally{
			l.debug("End get Nearest Origin Airport");
		}
	}


	private Location getFlickrLocationData(String url) {
		l.debug("Start get Flickr location data");
		try {
			return 	FlickrConnector.getPhotoDetailsMessageFromUrl(url);
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new GenericException(GenericException.UNABLE_TO_LOCATE_THE_IMAGE, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_LOCATE_THE_IMAGE));
		}finally{
			l.debug("Start get Flickr location data");
		}

	}
	private OfferGroupVO buildBestOffer(List<Offer> offers,List<CarDetailsVO> carDetailsVOs, List<HotelDetailsVO> hotelDetailsVOs) {

		OfferGroupVO offerGroupVO = null;
		if(offers != null && !offers.isEmpty()) {
			offerGroupVO = new OfferGroupVO();
			Collections.sort(offers, new AirOfferComparator());
			int recommendedAirOfferCount =  Integer.parseInt(PropertiesHolder.getProperty(OfferConstants.OFFER_PROPERTIES, OfferConstants.RECOMMENDED_AIR_OFFER_COUNT));
			if(offers.size() > recommendedAirOfferCount) {
				offers = offers.subList(0, recommendedAirOfferCount);
			}
			Offer bestAirOffer = offers.get(0);
			double totalPrice = bestAirOffer.getPrice();
			CarDetailsVO bestCarOffer =  null;
			if(carDetailsVOs != null) {
				// Car Details VO is already sorted
				bestCarOffer = carDetailsVOs.get(0);
				totalPrice = ArithmeticUtil.add(totalPrice,bestCarOffer.getTotalCost());
			}

			HotelDetailsVO bestHotelOffer = null;
			if(hotelDetailsVOs != null){
				bestHotelOffer = hotelDetailsVOs.get(0);
				totalPrice = ArithmeticUtil.add(totalPrice,bestHotelOffer.getPrice());
			}

			offerGroupVO.setAirOffers(offers);
			offerGroupVO.setCarOffers(carDetailsVOs);
			offerGroupVO.setHotelOffers(hotelDetailsVOs);
			RecommendedOfferVO recommendedOfferDtlsVO = new RecommendedOfferVO();
			recommendedOfferDtlsVO.setAirOffer(bestAirOffer);
			recommendedOfferDtlsVO.setCarOffer(bestCarOffer);
			recommendedOfferDtlsVO.setHotelOffer(bestHotelOffer);
			recommendedOfferDtlsVO.setTotalPrice(totalPrice);
			recommendedOfferDtlsVO.setCurrency(bestAirOffer.getCurrency());
			offerGroupVO.setRecommendedOfferVO(recommendedOfferDtlsVO);

		}
		return offerGroupVO;

	}

}
