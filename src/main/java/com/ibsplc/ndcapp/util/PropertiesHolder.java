package com.ibsplc.ndcapp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesHolder {
	private static Map<String, Map<String, String>> propertiesMap;
	public static String getProperty(String propertyName, String key) {
		if (propertiesMap == null) {
			propertiesMap = new HashMap<String, Map<String, String>>();
		}
		Map<String, String> properties = propertiesMap.get(propertyName);
		if(properties == null) {
			properties = loadProperties(propertyName);
			propertiesMap.put(propertyName, properties);
		}
		return properties.get(key);
	}
	private static Map<String, String> loadProperties(String propertyName) {
		Map<String, String> propertyMap = new HashMap<String, String>();
		try {
			Properties properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyName+".properties"));
//			PropertiesHolder.class.getResourceAsStream(propertyName+".properties");
//			
			Set<Entry<Object, Object>> entries = properties.entrySet();
			for(Entry entry : entries) {
				propertyMap.put((String)entry.getKey(), (String)entry.getValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyMap;
	}
	
	
}
