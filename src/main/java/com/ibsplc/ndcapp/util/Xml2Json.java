package com.ibsplc.ndcapp.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static String convertXml2Json(String xml){
		
		String jsonPrettyPrintString=null;
		  try {
	            JSONObject xmlJSONObj = XML.toJSONObject(xml);
	             jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	            System.out.println(jsonPrettyPrintString);
	        } catch (JSONException je) {
	            System.out.println(je.toString());
	        }
		  
		  return jsonPrettyPrintString;
	}
}
