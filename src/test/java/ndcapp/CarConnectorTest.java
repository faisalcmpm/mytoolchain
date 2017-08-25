//package ndcapp;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import junit.framework.TestCase;
//
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.junit.Test;
//
//import com.ibsplc.ndcapp.carrental.CarRentalConnector;
//import com.ibsplc.ndcapp.carrental.CarRentalConstants;
//import com.ibsplc.ndcapp.carrental.vo.CarDetailsRequestVO;
//import com.ibsplc.ndcapp.carrental.vo.CarDetailsVO;
//import com.ibsplc.ndcapp.hotel.HotelConnector;
//import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;
//import com.ibsplc.ndcapp.hotel.vo.HotelRequestVO;
//import com.ibsplc.ndcapp.util.PropertiesHolder;
//
//public class CarConnectorTest extends TestCase {
//	@Test
//	public static void testCar() throws JsonParseException, JsonMappingException, IOException {
//		System.out.println("Start Test testCar");
//		CarDetailsRequestVO requestVO = new CarDetailsRequestVO();
//		requestVO.setCountry("UK");
//		requestVO.setDriverAge(PropertiesHolder.getProperty(CarRentalConstants.CAR_RENTAL_PROPERTIES, CarRentalConstants.CAR_DRIVER_AGE));
//		requestVO.setDropOffLocationLatitute("51.1536621");
//		requestVO.setDropOffLocationLongitute("-0.18206290000000536");
//
//		requestVO.setLocale("en_GB");
//		requestVO.setPickupLocationLatitute("51.470020");
//		requestVO.setPickupLocationLongitute("-0.454295");
//		requestVO.setRequestCurrency("GBP");
//		Calendar pickupTime = new GregorianCalendar(2017, Calendar.JUNE, 7);
//		requestVO.setPickupTime(pickupTime);
//		Calendar dropOffTime = new GregorianCalendar(2017, Calendar.JUNE, 8);
//		requestVO.setDropOffTime(dropOffTime);
//		List<CarDetailsVO> carDetailsVOs = new CarRentalConnector().getLowestCarPrice(requestVO);
//		System.out.println(carDetailsVOs);
//		assertNotNull(carDetailsVOs);
//		System.out.println("End Test testCar");
//	}
//
//}
