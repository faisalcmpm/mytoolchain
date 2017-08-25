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
//import com.ibsplc.ndcapp.hotel.HotelConnector;
//import com.ibsplc.ndcapp.hotel.vo.HotelDetailsVO;
//import com.ibsplc.ndcapp.hotel.vo.HotelRequestVO;
//
//public class HotelConnectorTest extends TestCase {
//	@Test
//	public static void testHotel() throws JsonParseException, JsonMappingException, IOException {
//		System.out.println("Start Test testHotel");
//		HotelRequestVO hotelRequestVO = new HotelRequestVO();
//		hotelRequestVO.setCheckinDate(new GregorianCalendar(2017,Calendar.JUNE,1));
//		hotelRequestVO.setCheckoutDate(new GregorianCalendar(2017,Calendar.JUNE,2));
//		hotelRequestVO.setCountry("UK");
//		hotelRequestVO.setLocale("en_GB");
//		hotelRequestVO.setNumberOfGuests(1);
//		hotelRequestVO.setNumberOfRooms(1);
//		hotelRequestVO.setRequestCurrency("GBP");
//		hotelRequestVO.setStayLatitute("51.470020"); 
//		hotelRequestVO.setStayLongitude("-0.454295");
//		List<HotelDetailsVO> hotelDetailsVOs = new HotelConnector().getLowestHotelPrices(hotelRequestVO);
//		System.out.println(hotelDetailsVOs);
//		assertNotNull(hotelDetailsVOs);
//		System.out.println("End  Test testHotel");
//	}
//	
//}
