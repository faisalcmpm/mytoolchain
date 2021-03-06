package com.ibsplc.ndcapp.util;

public class StringUtils {

	public static int ordinalIndexOf(String str, String substr, int n) {
		
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
}
