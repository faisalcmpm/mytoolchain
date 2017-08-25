package com.ibsplc.ndcapp.test;

import com.ibsplc.ndcapp.air.connector.ConnectionConstants;
import com.ibsplc.ndcapp.util.PropertiesHolder;

public class TestPropertiesHolder {
	public static void main(String[] args) {
		System.out.println(PropertiesHolder.getProperty(ConnectionConstants.CNX_PROPERTY_FILE_NAME, "XQ."+ConnectionConstants.END_POINT_URL));
	}

}
