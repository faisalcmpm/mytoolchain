package com.ibsplc.ndcapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpPostClient {

	public String post(String url, String message, int timeout, Map<String, String> headers) throws HttpException, IOException {

		//		HttpClient httpClient = new HttpClient();	
		//		PostMethod postMethod = new PostMethod(url);
		//		postMethod.getParams().setParameter("http.socket.timeout", timeout);
		//		int result = httpClient.executeMethod(postMethod);		
		//		String response = postMethod.getResponseBodyAsString();

//		URL endPointURL = new URL(url);
//		byte[] postDataBytes = message.toString().getBytes("UTF-8");
//
//		HttpURLConnection conn = (HttpURLConnection)endPointURL.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//		conn.setRequestProperty("Authorization", "591440f04ea461b02b6736c2832e50b230e240ec4ac1ab0537c58f8f");
//		conn.setDoOutput(true);
//		conn.getOutputStream().write(postDataBytes);
//		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//		StringBuilder response = new StringBuilder();
//		for (int c; (c = in.read()) >= 0;) {
//			response.append((char)c);
//		}

		System.out.println("Request :-"+ message);
		
		HttpPost request = new HttpPost(url);
		StringEntity params =new StringEntity(message);
		if (headers != null && !headers.isEmpty()) {
			for(Map.Entry<String, String> headerEntry : headers.entrySet()) {
				request.addHeader(headerEntry.getKey(), headerEntry.getValue());		
			}
		}
		request.addHeader("content-type", "application/xml");
//		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		request.addHeader("Content-Length", String.valueOf(postDataBytes.length));
		request.setEntity(params);
		HttpClient httpClient = new DefaultHttpClient(); 
		HttpResponse httpResponse = httpClient.execute(request);
		

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				(httpResponse.getEntity().getContent())));
		StringBuilder result = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			result.append(line);
		}
		System.out.println("Response :-"+ result.toString());
		return result.toString();
	}

}
