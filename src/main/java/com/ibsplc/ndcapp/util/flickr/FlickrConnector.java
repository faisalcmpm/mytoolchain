package com.ibsplc.ndcapp.util.flickr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.util.StringUtils;
import com.ibsplc.ndcapp.util.flickr.json.Country;
import com.ibsplc.ndcapp.util.flickr.json.Locality;
import com.ibsplc.ndcapp.util.flickr.json.Location;
import com.ibsplc.ndcapp.util.flickr.vo.LocalePhotos;
import com.ibsplc.ndcapp.util.flickr.vo.PhotoDetails;


public class FlickrConnector {

	final static Logger log = Logger.getLogger("FlickrConnector.class");


	/*
	 * This class is for test purposes only
	 */
	/*public static void main(String[] args) throws IOException {

		Location location = getPhotoDetailsMessageFromUrl("https://www.flickr.com/photos/albionharrisonnaish/7163283745/in/feed");
		PhotoDetails photoDetails = new PhotoDetails();
		String carDetailsMessage = getPhotoDetailsMessage("C:\\Users\\A-6755\\Desktop\\hackathon\\photo_details.txt");
		photoDetails = parseJsonMessage(photoDetails, carDetailsMessage);
		System.out.println(photoDetails	);

		LocalePhotos localePhotos = new LocalePhotos();
		String localephotosString = getPhotoDetailsMessage("C:\\Users\\A-6755\\Desktop\\hackathon\\locale_photos_details.txt");
		localePhotos = parseJsonMessage(localePhotos, localephotosString);
		System.out.println(localePhotos);
	}*/


	/*	private static String getPhotoDetailsMessage(String file) throws IOException {
		log.debug("get ");
		BufferedReader reader = new BufferedReader(new FileReader(file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }		
	}*/


	public static <T> T parseJsonMessage(T object,String jsonMessage) throws JsonParseException, JsonMappingException, IOException {
		log.debug("Start parsing JSON Message");
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure( Feature.FAIL_ON_UNKNOWN_PROPERTIES , false);

		object = (T) mapper.readValue(jsonMessage, object.getClass());
		log.debug("End parsing JSON Message");
		return object;
	}





	public static Location getPhotoDetailsMessageFromUrl(String url) throws MalformedURLException, IOException {
		//		"https://www.flickr.com/photos/albionharrisonnaish/7163283745/in/feed"		
		log.debug("Start get Photo details from the Flickr url");
		if (true) {
			Location destinationLocation = new Location();
			destinationLocation.setLatitude("38.4189");
			destinationLocation.setLongitude("27.1287");
			Locality locality = new Locality();
			locality.set_content("Izmir");
			destinationLocation.setLocality(locality);
			Country country = new Country();
			country.set_content("Turkey");
			destinationLocation.setCountry(country);
			return destinationLocation;
		}
		if(url.indexOf("photos")!=-1){
			String urlShort = url.substring(url.indexOf("photos")+7);
			String id = "";
			int ordinalIndex = StringUtils.ordinalIndexOf(urlShort,"/", 1);
			int ordinalIndex2 = StringUtils.ordinalIndexOf(urlShort,"/", 2);
			if(ordinalIndex!=-1&&ordinalIndex2!=-1){
				id = urlShort.substring(ordinalIndex+1,ordinalIndex2);
			}

			ordinalIndex = StringUtils.ordinalIndexOf(urlShort,"\\", 1);
			ordinalIndex2 = StringUtils.ordinalIndexOf(urlShort,"\\", 2);
			if(ordinalIndex!=-1&&ordinalIndex2!=-1){
				id = urlShort.substring(ordinalIndex+1,ordinalIndex2);
			}

			log.debug("Get the photo details from the id in url");
			if(!id.equals("")){
				URLConnection uc = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.geo.getLocation&api_key="+APIKeys.FLICKR_KEY +
						"&photo_id="+id+"&format=json&nojsoncallback=1").openConnection();

				BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				StringBuilder result = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					result.append(line);
				}
				System.out.println(result.toString());

				PhotoDetails photoDetails = new PhotoDetails();
				photoDetails = parseJsonMessage(photoDetails, result.toString());
				System.out.println(photoDetails	);
				if(photoDetails!=null&&photoDetails.getPhoto()!=null){
					return photoDetails.getPhoto().getLocation();
				}
			}
		}
		return null;
	}

}
