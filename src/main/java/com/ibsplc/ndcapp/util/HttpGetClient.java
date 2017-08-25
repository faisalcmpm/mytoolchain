package com.ibsplc.ndcapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ibsplc.ndcapp.util.vo.JsonResponseVO;

public class HttpGetClient {

	
	public String get(String url,Map<String,String> parameterMap, int timeout, String acceptType) {
		
		HttpResponse response = null;
				
		try {

			String parameters = buildParameterString(parameterMap);
			if(parameters != null && !parameters.isEmpty()) {
				url = url+"?"+parameters;
			}
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					url);
			//	getRequest.addHeader("accept", "application/json");
			getRequest.addHeader("accept", acceptType);			

			response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();
			

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	
//public String getJSON(String url,Map<String,String> parameterMap, int timeout, String acceptType) {
		
public String getJSON(String url,Map<String,String> parameterMap, int timeout, Map<String,String> headerParameterMap){
	
		HttpResponse response = null;
		String output = null;
		String returnValue=null;

		try {

			String parameters = buildParameterString(parameterMap);
			if(parameters != null && !parameters.isEmpty()) {
				url = url+"?"+parameters;
			}
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					url);
			//	getRequest.addHeader("accept", "application/json");
			
		    
			if(headerParameterMap != null && !headerParameterMap.isEmpty()) {
			    Set<String> headerParameterNames = headerParameterMap.keySet();
			    for(String parameterName : headerParameterNames) {
			     getRequest.addHeader(parameterName, headerParameterMap.get(parameterName));  
			    }
			   }

			response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
				returnValue = output;

			}

			httpClient.getConnectionManager().shutdown();
			

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		return returnValue;
	}

public JsonResponseVO getJSONWithResponseHeaders(String url,Map<String,String> parameterMap, int timeout, Map<String,String> headerParameterMap){
	
	HttpResponse response = null;
	String output = null;
	String returnValue=null;
	
	JsonResponseVO responseVO = new JsonResponseVO();

	try {

		String parameters = buildParameterString(parameterMap);
		if(parameters != null && !parameters.isEmpty()) {
			url = url+"?"+parameters;
		}
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
				url);
		//	getRequest.addHeader("accept", "application/json");
		
	    
		if(headerParameterMap != null && !headerParameterMap.isEmpty()) {
		    Set<String> headerParameterNames = headerParameterMap.keySet();
		    for(String parameterName : headerParameterNames) {
		     getRequest.addHeader(parameterName, headerParameterMap.get(parameterName));  
		    }
		   }

		response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));

		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {

			System.out.println(output);
			returnValue = output;

		}

		httpClient.getConnectionManager().shutdown();
		

	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	Map<String,String> headerMap = null;
	Header[] headers = response.getAllHeaders();
	if(headers != null && headers.length > 0) {
		headerMap = new HashMap<String, String>();
		for(Header header : headers) {
			headerMap.put(header.getName(), header.getValue());
		}
	}
	responseVO.setResponse(returnValue);
	responseVO.setResponseHeaders(headerMap);

	return responseVO;
}
	private String buildParameterString(Map<String, String> parameterMap) {
		StringBuilder builder = new StringBuilder();
		if(parameterMap != null && !parameterMap.isEmpty()) {
			Set<String> parameterNames = parameterMap.keySet();
			int count = 0;
			for(String parameterName : parameterNames) {
				count++;
				builder.append(parameterName).append("=").append(parameterMap.get(parameterName));
				if(count != parameterNames.size()) {
					builder.append("&");
				}
			}
		}
		
		return builder.toString();
		// TODO Auto-generated method stub
		
	}
	
   public static void main(String args[])	{
	   Map<String,String> parameterMap = new HashMap<>();
	   parameterMap.put("format", "json");
	   String  returnValue = new HttpGetClient().getJSON("https://api.ipify.org/", parameterMap, 1000,  null);
	   System.out.println("result:: " + returnValue);
   }
}
