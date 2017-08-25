//package ndcapp;
//
//import java.util.Calendar;
//import java.util.Currency;
//import java.util.GregorianCalendar;
//
//import junit.framework.TestCase;
//
//import com.ibsplc.ndcapp.air.connector.NdcConnector;
//import com.ibsplc.ndcapp.air.connector.impl.XQNdcConnector;
//import com.ibsplc.ndcapp.air.model.AirShoppingRequest;
//import com.ibsplc.ndcapp.air.model.AirShoppingResponse;
//
//
//public class XQNdcConnectionTest extends TestCase{
//
//	public void testXQNdc() {
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
//		assertNotNull(response.getOffers());
//		System.out.println("End Test testXQNdc");
//	}
//
//}
