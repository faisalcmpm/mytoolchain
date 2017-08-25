package com.ibsplc.ndcapp.util.googleVision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.util.googleVision.vo.GoogleVisionDetails;
import com.ibsplc.ndcapp.util.googleVision.vo.LocationDetails;
import com.ibsplc.ndcapp.util.googleVision.vo.LocationDetailsFromName;
import com.ibsplc.ndcapp.util.googleVision.vo.Locations;

public class GoogleVisionInterface {

	final static Logger log = Logger.getLogger("FlickrConnector.class");

	public static LocationDetails postVisionRequest(String imageUrl){
		try {

			LocationDetails locationDetails = new LocationDetails();

			HttpClient httpClient = new DefaultHttpClient(); 

			try {

				HttpPost request = new HttpPost("https://vision.googleapis.com/v1/images:annotate?key="+APIKeys.GOOGLE_KEY);
				StringEntity params =new StringEntity("{\"requests\":[{\"image\":{\"source\":"
						+ "{\"imageUri\":\""+imageUrl +"\"}},\"features\""
						+ ":[{\"type\":\"LANDMARK_DETECTION\",\"maxResults\":10},"
						+ "{\"type\":\"WEB_DETECTION\",\"maxResults\":10}]}]}");
				request.addHeader("content-type", "application/json");
				request.setEntity(params);
				HttpResponse response = httpClient.execute(request);


				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatusLine().getStatusCode());
				}

				//				BufferedReader br = new BufferedReader(new InputStreamReader(
				//						(response.getEntity().getContent())));
				//
				//				String output;
				//				log.debug("Output from Server .... \n");
				//				while ((output = br.readLine()) != null) {
				//
				//					log.debug(output);
				//				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(
						(response.getEntity().getContent())));
				StringBuilder result = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					result.append(line);
				}
				log.debug(result.toString());

				GoogleVisionDetails googleVisionDetails = new GoogleVisionDetails();
				googleVisionDetails = parseJsonMessage(googleVisionDetails, result.toString());
				if(googleVisionDetails.getResponses()!=null){
					Locations[] location = null;
					String desc = null;
					if(googleVisionDetails.getResponses()[0].getLandmarkAnnotations()!=null){
						desc = googleVisionDetails.getResponses()[0].getLandmarkAnnotations()[0].getDescription();
						location = googleVisionDetails.getResponses()[0].getLandmarkAnnotations()[0].getLocations();
					}
					else{
						log.debug(desc = googleVisionDetails.getResponses()[0].getWebDetection().getWebEntities()[0].getDescription());
						desc = URLEncoder.encode(desc);
						URLConnection uc = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+desc).openConnection();

						BufferedReader locReader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
						StringBuilder locResult = new StringBuilder();
						String locLine;
						while((locLine = locReader.readLine()) != null) {
							locResult.append(locLine);
						}
						log.debug("Location Result"+locResult.toString());

						LocationDetailsFromName detailsFromName = new LocationDetailsFromName();
						detailsFromName = parseJsonMessage(detailsFromName, locResult.toString());
						if(detailsFromName.getResults()!=null){
							if(detailsFromName.getResults()[0].getFormatted_address()!=null){
								desc = detailsFromName.getResults()[0].getFormatted_address();
							}
							if(detailsFromName.getResults()[0].getGeometry()!=null){
								detailsFromName.getResults()[0].getGeometry().getLocation().getLat();
								detailsFromName.getResults()[0].getGeometry().getLocation().getLng();
								log.debug("description : "+desc+"latitude :"
										+detailsFromName.getResults()[0].getGeometry().getLocation().getLat()
										+"  lang : "+

							detailsFromName.getResults()[0].getGeometry().getLocation().getLng());
								locationDetails.setAddress(desc);
								locationDetails.setLat(detailsFromName.getResults()[0].getGeometry().getLocation().getLat());
								locationDetails.setLng(detailsFromName.getResults()[0].getGeometry().getLocation().getLng());
							}
						}
					}
					if(location!=null){
						log.debug("description : "+desc+"latitude :"+location[0].getLatLng().getLatitude()+"  lang : "+location[0].getLatLng().getLongitude());

						locationDetails.setAddress(desc);
						locationDetails.setLat(location[0].getLatLng().getLatitude());
						locationDetails.setLng(location[0].getLatLng().getLongitude());
					}
				}

				return locationDetails;
			}catch (Exception ex) {
				throw  ex;
			} finally {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> T parseJsonMessage(T object,String jsonMessage) throws JsonParseException, JsonMappingException, IOException {
		log.debug("Start parsing JSON Message");
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure( Feature.FAIL_ON_UNKNOWN_PROPERTIES , false);

		object = (T) mapper.readValue(jsonMessage, object.getClass());
		log.debug("End parsing JSON Message");
		return object;
	}
}
