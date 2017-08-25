package com.ibsplc.ndcapp.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ibsplc.ndcapp.hotel.json.Agent_prices;
import com.ibsplc.ndcapp.hotel.json.HotelResponse;
import com.ibsplc.ndcapp.hotel.json.Hotels;
import com.ibsplc.ndcapp.hotel.json.Hotels_prices;
import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;
import com.ibsplc.ndcapp.hotel.vo.HotelRequestVO;
import com.ibsplc.ndcapp.util.ArithmeticUtil;
import com.ibsplc.ndcapp.util.CalendarUtil;
import com.ibsplc.ndcapp.util.HttpGetClient;
import com.ibsplc.ndcapp.util.JsonUtil;
import com.ibsplc.ndcapp.util.PropertiesHolder;
import com.ibsplc.ndcapp.util.vo.JsonResponseVO;

public class HotelConnector {
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		HotelRequestVO hotelRequestVO = new HotelRequestVO();
		hotelRequestVO.setCheckinDate(new GregorianCalendar(2017,Calendar.JUNE,1));
		hotelRequestVO.setCheckoutDate(new GregorianCalendar(2017,Calendar.JUNE,2));
		hotelRequestVO.setCountry("UK");
		hotelRequestVO.setLocale("en_GB");
		hotelRequestVO.setNumberOfGuests(1);
		hotelRequestVO.setNumberOfRooms(1);
		hotelRequestVO.setRequestCurrency("GBP");
		hotelRequestVO.setStayLatitute("51.470020"); 
		hotelRequestVO.setStayLongitude("-0.454295");
		List<HotelDetailsVO> hotelDetailsVOs = new HotelConnector().getLowestHotelPrices(hotelRequestVO);
		System.out.println(hotelDetailsVOs);
		/*HttpGetClient client = new HttpGetClient();
		JsonResponseVO response = client.getJSONWithResponseHeaders("http://partners.api.skyscanner.net/apiservices/hotels/liveprices/v2/UK/GBP/en_GB/27539733/2017-06-01/2017-06-02/1/1?apiKey=ha435590125388369458987362743089", null, 1000, null);
		System.out.println(response);
		HotelResponse test = new HotelResponse();
		test = JsonUtil.parseJsonMessage(test, response.getResponse());
		System.out.println(test);*/
	}
	
	public List<HotelDetailsVO> getLowestHotelPrices(HotelRequestVO hotelRequestVO) throws JsonParseException, JsonMappingException, IOException {
//		if (true) {
//			return buildHotelDetailsVOsNew("TL");
//		}
		List<HotelDetailsVO> hotelDetailsVOs = null;
		int maxRetrievalCount = 3;
		String url = PropertiesHolder.getProperty(HotelConstants.HOTEL_PROPERTIES, HotelConstants.HOTEL_END_POINT);
		String request = getHotelSessionCreateRequest(hotelRequestVO,url);
		HttpGetClient client = new HttpGetClient();
		Map<String,String> parameterMap = new HashMap();
		String apiKey = PropertiesHolder.getProperty(HotelConstants.HOTEL_PROPERTIES, HotelConstants.HOTEL_API_KEY);
		parameterMap.put("apiKey",apiKey);
		parameterMap.put("Content-Type", "application/json");
		JsonResponseVO jsonResponseVO = client.getJSONWithResponseHeaders(request, parameterMap, 1000, null);
		HotelResponse hotelResponse = new HotelResponse();
		hotelResponse = JsonUtil.parseJsonMessage(hotelResponse, jsonResponseVO.getResponse());
		int count = 0;
		// If the response creation is not complete during session creation, we will try to poll again
		while(!"COMPLETE".equals(hotelResponse.getStatus()) && count < maxRetrievalCount) {
			if(jsonResponseVO.getResponseHeaders() != null && jsonResponseVO.getResponseHeaders().get("Location") != null) {
				String pollingUrl = jsonResponseVO.getResponseHeaders().get("Location");
				parameterMap = null;
				url =  PropertiesHolder.getProperty(HotelConstants.HOTEL_PROPERTIES, HotelConstants.HOTEL_RETRY_END_POINT);
				request = url+pollingUrl;
				jsonResponseVO = client.getJSONWithResponseHeaders(request, parameterMap, 1000, null);
				hotelResponse = new HotelResponse();
				hotelResponse = JsonUtil.parseJsonMessage(hotelResponse, jsonResponseVO.getResponse());
				count++;
			}
		}
		
		if(hotelResponse != null && hotelResponse.getHotels() != null && hotelResponse.getHotels().length > 0) {
			hotelDetailsVOs = buildHotelDetailsVOs(hotelResponse,hotelRequestVO.getRequestCurrency());
			if(hotelDetailsVOs != null) {
				Collections.sort(hotelDetailsVOs,new HotelDetailsComparator());
				int recommendedHotelCount = Integer.parseInt(PropertiesHolder.getProperty(HotelConstants.HOTEL_PROPERTIES, HotelConstants.RECOMMENDED_HOTEL_COUNT));
				if(hotelDetailsVOs.size() > recommendedHotelCount) {
					hotelDetailsVOs = hotelDetailsVOs.subList(0, recommendedHotelCount);
				}
			}
		}
		
		return hotelDetailsVOs;
	}

	private List<HotelDetailsVO> buildHotelDetailsVOs(HotelResponse hotelResponse,String currency) {
		List<HotelDetailsVO> hotelDetailsVOs = null;
		if(hotelResponse.getHotels() != null) {
			hotelDetailsVOs = new ArrayList<HotelDetailsVO>();
			Map<String,Agent_prices> lowestPriceHotelAgentMap = getLowestPriceHotelAgentMap(hotelResponse);
			for(Hotels hotel : hotelResponse.getHotels()) {
				HotelDetailsVO hotelDetailsVO = new HotelDetailsVO();
				hotelDetailsVO.setHotelName(hotel.getName());
				hotelDetailsVO.setLatitude(hotel.getLatitude());
				hotelDetailsVO.setLongitude(hotel.getLongitude());
				hotelDetailsVO.setStarRating(hotel.getStar_rating());
				hotelDetailsVO.setPublicRating(hotel.getPopularity_desc());
				if(hotel.getAmenities() != null && hotel.getAmenities().length > 0) {
					hotelDetailsVO.setAmenities(Arrays.asList(hotel.getAmenities()));
				}
				if(lowestPriceHotelAgentMap.get(hotel.getHotel_id())  != null) {
					hotelDetailsVO.setPrice(ArithmeticUtil.round(Double.parseDouble(lowestPriceHotelAgentMap.get(hotel.getHotel_id()).getPrice_total()),2));
				}
				hotelDetailsVO.setCurrency(currency);
				hotelDetailsVOs.add(hotelDetailsVO);
			}
		}
		return hotelDetailsVOs;
	}
	private List<HotelDetailsVO> buildHotelDetailsVOsNew(String currency) {
		List<HotelDetailsVO> hotelDetailsVOs = null;
			hotelDetailsVOs = new ArrayList<HotelDetailsVO>();
			
				HotelDetailsVO hotelDetailsVO = new HotelDetailsVO();
				hotelDetailsVO.setHotelName("Clayton Turkey");
//				hotelDetailsVO.setLatitude(hotel.getLatitude());
//				hotelDetailsVO.setLongitude(hotel.getLongitude());
				hotelDetailsVO.setStarRating("3");
				hotelDetailsVO.setPublicRating("Good");				
				
				hotelDetailsVO.setPrice(1000);
				
				hotelDetailsVO.setCurrency(currency);
				hotelDetailsVOs.add(hotelDetailsVO);
				
				hotelDetailsVO = new HotelDetailsVO();
				hotelDetailsVO.setHotelName("Akra Barut");
//				hotelDetailsVO.setLatitude(hotel.getLatitude());
//				hotelDetailsVO.setLongitude(hotel.getLongitude());
				hotelDetailsVO.setStarRating("5");
				hotelDetailsVO.setPublicRating("Excellent");				
				
				hotelDetailsVO.setPrice(2000);
				
				hotelDetailsVO.setCurrency(currency);
				hotelDetailsVOs.add(hotelDetailsVO);
		return hotelDetailsVOs;
	}
	private Map<String, Agent_prices> getLowestPriceHotelAgentMap(HotelResponse hotelResponse) {
		Map<String,Agent_prices> hotelAgentPriceMap = new HashMap();
		if(hotelResponse.getHotels_prices() != null && hotelResponse.getHotels_prices().length > 0) {
			for(Hotels_prices hotelPrice : hotelResponse.getHotels_prices()) {
				if(hotelPrice.getAgent_prices() != null && hotelPrice.getAgent_prices().length > 0) {
					for(Agent_prices agentPrice : hotelPrice.getAgent_prices()) {
						if(agentPrice != null) {
							if(hotelAgentPriceMap.get(hotelPrice.getId()) == null) {
								hotelAgentPriceMap.put(hotelPrice.getId(),agentPrice);
							}else{
								if(Double.parseDouble(agentPrice.getPrice_total()) < Double.parseDouble(hotelAgentPriceMap.get(hotelPrice.getId()).getPrice_total())){
									hotelAgentPriceMap.put(hotelPrice.getId(),agentPrice);
								}
							}
						}
					}
				}
				
			}
		}
		return hotelAgentPriceMap;
	}

	private String getHotelSessionCreateRequest(HotelRequestVO hotelRequestVO,String url) {
		List<String> requestParameters = new ArrayList<String>();
		requestParameters.add(hotelRequestVO.getCountry());
		requestParameters.add(hotelRequestVO.getRequestCurrency());
		requestParameters.add(hotelRequestVO.getLocale());
		String stayNav = hotelRequestVO.getStayLatitute()+","+hotelRequestVO.getStayLongitude()+"-latlong";
		requestParameters.add(stayNav);
		String checkInDate = CalendarUtil.toStringFormat(hotelRequestVO.getCheckinDate(),  "yyyy-MM-dd");
		requestParameters.add(checkInDate);
		String checkoutDate =  CalendarUtil.toStringFormat(hotelRequestVO.getCheckoutDate(),  "yyyy-MM-dd");
		requestParameters.add(checkoutDate);
		requestParameters.add(String.valueOf(hotelRequestVO.getNumberOfGuests()));
		requestParameters.add(String.valueOf(hotelRequestVO.getNumberOfRooms()));
		String request = url+getFormattedRequest(requestParameters)+"/";
		return request;
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
