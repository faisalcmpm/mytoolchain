package com.ibsplc.ndcapp.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static <T> T parseJsonMessage(T object,String jsonMessage) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		object = (T) mapper.readValue(jsonMessage, object.getClass());
		return object;
	}
	
	public static String buildJsonMessage(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();		
		mapper.writeValue(sw, object);
		String message = sw.getBuffer().toString();
		return message;
		
	}

}
