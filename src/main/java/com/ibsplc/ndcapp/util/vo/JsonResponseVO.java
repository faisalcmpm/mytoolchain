package com.ibsplc.ndcapp.util.vo;

import java.util.Map;

public class JsonResponseVO {
	
	private String response;
	
	private Map<String,String> responseHeaders;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
	
	
	

}
