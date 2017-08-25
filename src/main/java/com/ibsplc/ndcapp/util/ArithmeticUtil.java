package com.ibsplc.ndcapp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ArithmeticUtil {

	
	
	private static final String DOT = ".";

	
	private static final String ZERO = "0";

	
	public static double round(double value, int precision) {
		BigDecimal valueBD = new BigDecimal(String.valueOf(value)).setScale(precision, BigDecimal.ROUND_HALF_UP);
		double roundedValue = valueBD.doubleValue();
		valueBD = null;
		return roundedValue;
	}
	
	public static double round(String value, int precision) {
		BigDecimal valueBD = new BigDecimal(value).setScale(precision, BigDecimal.ROUND_HALF_UP);
		double roundedValue = valueBD.doubleValue();
		valueBD = null;
		return roundedValue;
	}
	
	public static double add(double firstValue, double secondValue) {
		BigDecimal firstValueBD = new BigDecimal(String.valueOf(firstValue));
		BigDecimal secondValueBD = new BigDecimal(String.valueOf(secondValue));
		double sum = firstValueBD.add(secondValueBD).doubleValue();
		firstValueBD = null;
		secondValueBD = null;
		return sum;
	}

	
	public static double subtract(double firstValue, double secondValue) {
		BigDecimal firstValueBD = new BigDecimal(String.valueOf(firstValue));
		BigDecimal secondValueBD = new BigDecimal(String.valueOf(secondValue));
		double difference = firstValueBD.subtract(secondValueBD).doubleValue();
		firstValueBD = null;
		secondValueBD = null;
		return difference;
	}

	
	public static double multiply(double firstValue, double secondValue) {
		BigDecimal firstValueBD = new BigDecimal(String.valueOf(firstValue));
		BigDecimal secondValueBD = new BigDecimal(String.valueOf(secondValue));
		double product = firstValueBD.multiply(secondValueBD).doubleValue();
		firstValueBD = null;
		secondValueBD = null;
		return product;
	}

	
	public static double divide(double firstValue, double secondValue, int scale) {
		BigDecimal firstValueBD = new BigDecimal(String.valueOf(firstValue));
		BigDecimal secondValueBD = new BigDecimal(String.valueOf(secondValue));
		double quotient = firstValueBD.divide(secondValueBD, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		firstValueBD = null;
		secondValueBD = null;
		return quotient;
	}

	
	public static double trunc(double value, int precision) {
		BigDecimal valueBD = new BigDecimal(String.valueOf(value));
		BigDecimal multiplicationFactorBD = new BigDecimal(String.valueOf(Math.pow(10, precision)));
		double temp = valueBD.multiply(multiplicationFactorBD).doubleValue();
		double truncatedValue = ((long) temp) / Math.pow(10, precision);
		valueBD = null;
		multiplicationFactorBD = null;
		return truncatedValue;
	}

	
	public static String format(double value, int precision) {
		value = round(value, precision);
		StringBuffer pattern = new StringBuffer(ZERO);
		for (int i = 0; i < precision; i++) {
			if (i == 0) {
				pattern.append(DOT);
			}
			pattern.append(ZERO);
		}
		NumberFormat format = new DecimalFormat(pattern.toString());
		String formattedValue = format.format(value);
		format = null;
		pattern = null;
		return formattedValue;
	}

	
	public static String format(String value, int precision) {
		double valueDouble = round(value, precision);
		StringBuffer pattern = new StringBuffer(ZERO);
		for (int i = 0; i < precision; i++) {
			if (i == 0) {
				pattern.append(DOT);
			}
			pattern.append(ZERO);
		}
		NumberFormat format = new DecimalFormat(pattern.toString());
		String formattedValue = format.format(valueDouble);
		format = null;
		pattern = null;
		return formattedValue;
	}
}
