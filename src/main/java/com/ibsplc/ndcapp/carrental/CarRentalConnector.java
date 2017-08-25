package com.ibsplc.ndcapp.carrental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ibsplc.ndcapp.carrental.json.CarDetails;
import com.ibsplc.ndcapp.carrental.json.Car_classes;
import com.ibsplc.ndcapp.carrental.json.Cars;
import com.ibsplc.ndcapp.carrental.json.Images;
import com.ibsplc.ndcapp.carrental.json.Value_add;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsRequestVO;
import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;
import com.ibsplc.ndcapp.offer.OfferConstants;
import com.ibsplc.ndcapp.util.ArithmeticUtil;
import com.ibsplc.ndcapp.util.CalendarUtil;
import com.ibsplc.ndcapp.util.HttpGetClient;
import com.ibsplc.ndcapp.util.JsonUtil;
import com.ibsplc.ndcapp.util.PropertiesHolder;



public class CarRentalConnector {
	
	
	
	
	/*public static void main(String[] args) throws IOException {
		CarDetailsRequestVO requestVO = new CarDetailsRequestVO();
		requestVO.setCountry("UK");
		requestVO.setDriverAge(PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.CAR_DRIVER_AGE));
		requestVO.setDropOffLocationLatitute("51.1536621");
		requestVO.setDropOffLocationLongitute("-0.18206290000000536");
		
		requestVO.setLocale("en_GB");
		requestVO.setPickupLocationLatitute("51.470020");
		requestVO.setPickupLocationLongitute("-0.454295");
		requestVO.setRequestCurrency("GBP");
		Calendar pickupTime = new GregorianCalendar(2017, Calendar.JUNE, 7);
		requestVO.setPickupTime(pickupTime);
		Calendar dropOffTime = new GregorianCalendar(2017, Calendar.JUNE, 8);
		requestVO.setDropOffTime(dropOffTime);
		List<CarDetailsVO> carDetailsVOs = new CarRentalConnector().getLowestCarPrice(requestVO);
		System.out.println(carDetailsVOs);
	}*/
	
	public List<CarDetailsVO> getLowestCarPrice(CarDetailsRequestVO carDetailsRequestVO) throws JsonParseException, JsonMappingException, IOException {
		List<CarDetailsVO> carDetailsVOs = null;
		String carDetailsMessage = getCarDetailsMessage(carDetailsRequestVO);
		//System.out.println(carDetailsMessage);
		CarDetails carDetails = new CarDetails();
		carDetails = JsonUtil.parseJsonMessage(carDetails, carDetailsMessage);
		CarDetailsVO carDetailsVO = null;
		if(carDetails != null) {
			   carDetailsVOs = buildCarDetailsVOs(carDetails); 
			   if(carDetailsVOs != null && !carDetailsVOs.isEmpty()) {
				   Collections.sort(carDetailsVOs,new CarDetailsComparator());
				   int reccommendedCarCount = Integer.parseInt(PropertiesHolder.getProperty(OfferConstants.OFFER_PROPERTIES, OfferConstants.RECOMMENDED_CAR_OFFER_COUNT));
				   if(carDetailsVOs.size() > reccommendedCarCount) {
					   carDetailsVOs = carDetailsVOs.subList(0, reccommendedCarCount);
				   }
				  
			   }
				
		}
		//System.out.println(carDetailsVO);
		return carDetailsVOs;
		
	}
	
	private static List<CarDetailsVO> buildCarDetailsVOs(CarDetails carDetails) {
		Set<String> uniqueCarSet = new HashSet<String>();
		List<CarDetailsVO> carDetailsVOs = null;
		if(carDetails !=  null && carDetails.getCars() != null && carDetails.getCars().length > 0) {
			Map<String,Images> imageMap = buildImageMap(carDetails.getImages());
			Map<String,Car_classes> carClassMap = buildCarClassMap(carDetails.getCar_classes());
			carDetailsVOs = new ArrayList<>();
			for(Cars car : carDetails.getCars()) {
				CarDetailsVO carDetailsVO = new CarDetailsVO();
				carDetailsVO.setVehicleMake(car.getVehicle());
				carDetailsVO.setNumberOfseats(Integer.parseInt(car.getSeats()));
				if(imageMap !=  null && imageMap.get(car.getImage_id()) != null) {					
					carDetailsVO.setImageUrl(imageMap.get(car.getImage_id()).getUrl());
				}
				if(carClassMap != null && carClassMap.get(car.getCar_class_id()) != null) {
					carDetailsVO.setVehicleType(carClassMap.get(car.getCar_class_id()).getName());
				}
				carDetailsVO.setCurrency(carDetails.getSubmitted_query().getCurrency());
				carDetailsVO.setAirConditioned("true".equals(car.getAir_conditioning()));
				carDetailsVO.setCarRentalPrice(ArithmeticUtil.round(Double.parseDouble(car.getPrice_all_days()), 2));
				carDetailsVO.setNumberOfBags(Integer.parseInt(car.getBags()));				
				Value_add valueAdd = car.getValue_add();
				if(valueAdd != null && valueAdd.getAdditional_drivers() != null && valueAdd.getAdditional_drivers().getPaid() != null) {
					carDetailsVO.setAdditionalDriverPrice(ArithmeticUtil.round(Double.parseDouble(valueAdd.getAdditional_drivers().getPaid().getPrice()),2));
				}
				carDetailsVO.setTotalCost(ArithmeticUtil.add(carDetailsVO.getCarRentalPrice(), carDetailsVO.getAdditionalDriverPrice()));
				String carKey = carDetailsVO.getVehicleMake()+"_"+carDetailsVO.getTotalCost();
				if(!uniqueCarSet.contains(carKey)) {
					carDetailsVOs.add(carDetailsVO);
					uniqueCarSet.add(carKey);
				}
			}
		}
		return carDetailsVOs;
	}

	private static Map<String, Car_classes> buildCarClassMap(Car_classes[] carClasses) {
		Map<String,Car_classes> carClassMap = null;
		if(carClasses != null && carClasses.length > 0) {
			carClassMap = new HashMap<String, Car_classes>();
			for(Car_classes carClass : carClasses) {
				carClassMap.put(carClass.getId(), carClass);
			}
		}
		return carClassMap;
	}
	
	private static Map<String, Images> buildImageMap(Images[] images) {
		Map<String,Images> imageMap = null;
		if(images != null && images.length > 0) {
			imageMap = new HashMap<String, Images>();
			for(Images image : images) {
				imageMap.put(image.getId(), image);
			}
		}
		return imageMap;
	}

	private static String getCarDetailsMessage(CarDetailsRequestVO carDetailsRequestVO) throws IOException {
//		if(true) {
//			BufferedReader reader = new BufferedReader(new FileReader("D:\\sibu\\hackathon\\car_rental.txt"));
//			String         line = null;
//			StringBuilder  stringBuilder = new StringBuilder();
//			String         ls = System.getProperty("line.separator");
//
//			try {
//				while((line = reader.readLine()) != null) {
//					stringBuilder.append(line);
//					stringBuilder.append(ls);
//				}
//
//				return stringBuilder.toString();
//			} finally {
//				reader.close();
//			}        
//			
//		}
		String carRentalUrl = PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.CAR_RENTAL_END_POINT);
		List<String> requestParameters = new ArrayList<String>();
		requestParameters.add(carDetailsRequestVO.getCountry());
		requestParameters.add(carDetailsRequestVO.getRequestCurrency());
		requestParameters.add(carDetailsRequestVO.getLocale());
		String pickupNav = carDetailsRequestVO.getPickupLocationLatitute()+","+carDetailsRequestVO.getPickupLocationLongitute()+"-latlong";
		requestParameters.add(pickupNav);
		String dropOffNav = carDetailsRequestVO.getDropOffLocationLatitute()+","+carDetailsRequestVO.getDropOffLocationLongitute()+"-latlong";
		requestParameters.add(dropOffNav);
		String pickupTime = CalendarUtil.toStringFormat(carDetailsRequestVO.getPickupTime(), CalendarUtil.ADVANCED_REVERSE_DATE_FORMAT);
		String[] timeArray = pickupTime.split(" ");
		pickupTime = timeArray[0]+"T"+timeArray[1];
		requestParameters.add(pickupTime);
		String dropOffTime = CalendarUtil.toStringFormat(carDetailsRequestVO.getDropOffTime(), CalendarUtil.ADVANCED_REVERSE_DATE_FORMAT);
		timeArray = dropOffTime.split(" ");
		dropOffTime = timeArray[0]+"T"+timeArray[1];
		requestParameters.add(dropOffTime);
		requestParameters.add(carDetailsRequestVO.getDriverAge());
		carRentalUrl = carRentalUrl+getFormattedRequest(requestParameters)+"/";
		//String request = carDetailsRequestVO.getCountry()+"/"+carDetailsRequestVO.getRequestCurrency()+"/"+carDetailsRequestVO.getPickupLocationLatitute()+
				
		HttpGetClient getClient = new HttpGetClient();
		Map<String,String> parameterMap = new HashMap();
		String apiKey = PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.CAR_RENTAL_API_KEY);
		parameterMap.put("apiKey",apiKey);
		parameterMap.put("userip", PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.IP_ADDRESS));
		parameterMap.put("Content-Type", "application/json");
		String response = getClient.getJSON(carRentalUrl,	parameterMap, 10000,null);		
		//System.out.println(response);
		return response;
	}
	
	private static String getFormattedRequest(List<String> requestParameters) {
		StringBuilder stringBuilder = new StringBuilder();
		if(requestParameters != null && !requestParameters.isEmpty()) {
			int count = 0;
			for(String requestParameter : requestParameters) {
				count++;
				stringBuilder.append(requestParameter);
				if(count != requestParameters.size()) {
					stringBuilder.append("/");
				}
			}
		}
		return stringBuilder.toString();
	}

	

}
