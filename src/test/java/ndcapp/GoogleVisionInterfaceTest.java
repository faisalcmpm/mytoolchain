//package ndcapp;
//
//import com.ibsplc.ndcapp.util.googleVision.GoogleVisionInterface;
//import com.ibsplc.ndcapp.util.googleVision.vo.LocationDetails;
//
//import junit.framework.TestCase;
//
//public class GoogleVisionInterfaceTest extends TestCase {
//	public void testGoogleVisionInterface() {
//		GoogleVisionInterface googleVisionInterface = new GoogleVisionInterface();
//		LocationDetails location = googleVisionInterface.postVisionRequest("http://www.urlaubwelt.com/wp-content/uploads/2016/02/izmir-10.jpg");
//		System.out.println("Location "+location.getAddress());
//		assertNotNull(location.getAddress());
//	}
//}
