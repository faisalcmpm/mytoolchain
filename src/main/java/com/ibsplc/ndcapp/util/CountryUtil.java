package com.ibsplc.ndcapp.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CountryUtil {

	static Map<String, String> countries = new HashMap<>();
	static {

		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
		}

	}

	public static String getCountryCode(String countryName){
		    
		    return countries.get(countryName);
	}
}
