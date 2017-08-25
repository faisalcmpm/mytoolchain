
package com.ibsplc.ndcapp.util;




import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class CalendarUtil {


	/**
	 *
	 */
	public static final String NORMAL_DATE_FORMAT_WITHOUT_SECONDS = "dd-MM-yyyy HH:mm";

	/**
	 *
	 */
	public static final String CALENDAR_DATE_FORMAT = "dd-MMM-yyyy";
	/**
	 *
	 */
	public static final String CALENDAR_DATE_FORMAT_WITHOUT_HYPHEN = "ddMMMyyyy";
	/**
	 *
	 */
	public static final String CALENDAR_DATE_FORMATWITHYEAROPTIMIZED = "dd-MMM-YY";
	/**
	 * ADVANCED_DATE_FORMAT of type String
	 */
	public static final String ADVANCED_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	/**
	 * ADVANCED_REVERSE_DATE_FORMAT of type String
	 */
	public static final String ADVANCED_DATE_FORMAT_REVERSED = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ADVANCED_REVERSE_DATE_FORMAT
	 */
	public static final String ADVANCED_REVERSE_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * ADVANCED_REVERSE_DATE_FORMAT_WITH_SECONDS of type String
	 */
	public static final String ADVANCED_REVERSE_DATE_FORMAT_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ADVANCED_DATE_FORMAT_WITHOUT_SECONDS of type String
	 */
	public static final String ADVANCED_DATE_FORMAT_WITHOUT_SECONDS = "dd-MMM-yyyy HH:mm";
	/**
	 * ADVANCED_DATE_FORMAT_WITH_SECONDS of type String
	 */
	public static final String ADVANCED_DATE_FORMAT_WITH_SECONDS = "dd-MMM-yyyy HH:mm:ss";
	/**
	 *
	 */
	public static final String ADVANCED_DATE_FORMAT_WITH_SECONDS_12HR = "dd-MMM-yyyy hh:mm:ss";
	/**
	 *
	 */
	public static final String ADVANCED_DATE_FORMAT_WITHOUT_HIPHEN = "ddMMyy hhmm";
	/**
	 *
	 */
	public static final String ADVANCED_DATE_FORMAT_WITHOUT_HIPHEN_24HR = "ddMMyy HHmm";
	/**
	 *
	 */
	public static final String TIME_FORMAT = "HH:mm";
	/**
	 *
	 */
	public static final String AUDIT_TIME_FORMAT = "hh:mm:ss a";
	/**
	 *
	 */
	public static final String FULL_TIME_FORMAT = "HH:mm:ss";
	/**
	 *
	 */
	public static final String INFINITE_DATE_FORMAT = "31-12-9999 00:00:00";
	/**
	 *
	 */
	public static final String PURGING_DATE_FORMAT = "ddMMyyyy";	
/**
	 * This constant specifies the Number of Hours in a Day
	 */
	public static final int HOURS_IN_DAY = 24;
	/**
	 * This constant specifies te Number of Minutes per day
	 */

	public static final int MINUTES_IN_DAY = 1440;
	/**
	 * This constant specifies the Number of minutes in an hour
	 */
	public static final int MINUTES_IN_HOUR = 60;
	/**
	 * Specifies the Number of seconds in a minute
	 */
	public static final long SECONDS_IN_MINUTE = 60;
	/**
	 * Specifies the  Milliseconds per Minute
	 */
	public static final long MILLIS_IN_MINUTE = SECONDS_IN_MINUTE * 1000;

	/**
	 * This constant specifies the number of Milli seconds in an hour
	 */
	public static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * MINUTES_IN_HOUR;
	/**
	 * This constant specifies the number of Milli seconds in a day
	 */
	public static final long MILLIS_IN_DAY = MILLIS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;

	/**
	 * GMT
	 */
	public static final String GMT = "gmt";
	/**
	 * Delimitor used to separate Hours,Minute etc
	 */
	public static final String HOUR_MINUTE_DELIMITER = ":";
	/**
	 * Time Zone
	 */
	public static final TimeZone GMT_ZONE = TimeZone.getTimeZone(GMT);

	/**
	 *   DEFAULT_YEARSTART
	 */
	public static final int DEFAULT_YEARSTART = 1;

	/**
	 * PAST_YEARSTART
	 */
	public static final int PAST_YEARSTART = 2;

	/**
	 *
	 */
	public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();
	private static final String NORMAL_DATE_FORMAT = "dd-MON-YYYY";

	/**
	 * This method converts a calendar encapsulating a date in any timezone
	 * to a date in the default timezone of the jvm. This can be used to
	 * tackle the possible changes in date and time when a date is used
	 * across various jvms running in different timezones.
	 *
	 * Example:-
	 * When a date, say 21-06-2002 is passed from one jvm to another jvm
	 * in different timezones, it can be converted to 20-06-2002 or 22-06-2002.
	 * This method returns the same date 21-06-2002 irrespective of timezones.
	 *
	 * @param calendar date which has to be converted to the default timezone
	 * @return Calendar date converted to default timezone.
	 */
	public static Calendar toDefaultTimeZone(Calendar calendar) {
		Calendar localDate = new GregorianCalendar();
		localDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
			calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
		localDate.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		return localDate;
	}

	/**
	 * This method converts a calendar encapsulating a date in any timezone
	 * to a date in the default timezone of the jvm. This can be used to
	 * tackle the possible changes in date and time when a date is used
	 * across various jvms running in different timezones.
	 *
	 * Example:-
	 * When a date, say 21-06-2002 is passed from one jvm to another jvm
	 * in different timezones, it can be converted to 20-06-2002 or 22-06-2002.
	 * This method returns the same date 21-06-2002 irrespective of timezones.
	 *
	 * @param calendar date which has to be converted to the default timezone
	 * @return Date date converted to default timezone.
	 */
	public static Date getDate(Calendar calendar) {
		return toDefaultTimeZone(calendar).getTime();
	}

	/**
	 * This method converts a calendar encapsulating a date in any timezone
	 * to a Sql date in the default timezone of the jvm.
	 *
	 * @param calendar date which has to be converted to the default timezone
	 * @return SqlDate Date in default timezone.
	 */

	public static java.sql.Date getSqlDate(Calendar calendar) {
		return new java.sql.Date(getDate(calendar).getTime());
	}

	/**
	 * This method converts a calendar encapsulating a date in any timezone
	 * to a Timestamp in the default timezone of the jvm.
	 *
	 * @param calendar date which has to be converted to the default timezone
	 * @return Timestamp timestamp in default timezone.
	 */
	public static Timestamp getTimestamp(Calendar calendar) {
		return new Timestamp(getDate(calendar).getTime());
	}

	/**
	 * This method converts a date in the server time zone to a calendar
	 * in the same timezone
	 *
	 * @param date date which has to be converted to Calendar in
	 *        the default timezone
	 * @return Calendar in default timezone.
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * This method forces the hours, minutes, seconds, and milliseconds
	 * in a date object to 0 but keeps the date part the same.
	 * This will return the date in the default timezone of the jvm
	 * in which this class is loaded.
	 *
	 * @param dateWithTime the date whose time fields are to be trimmed off.
	 * @return Date a date object with all time fields trimmed off.
	 */
	public static Date dateOnly(Date dateWithTime) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateWithTime);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	/**
	 * This method forces the hours, minutes, seconds, and milliseconds
	 * in a calendar object to 0 but keeps the date part the same.
	 *
	 * @param dateWithTime the calendar whose time fields are to be trimmed off.
	 * @return a modified calendar object with all time fields trimmed off.
	 */
	public static Calendar dateOnly(Calendar dateWithTime) {
		dateWithTime.set(Calendar.HOUR, 0);
		dateWithTime.set(Calendar.MILLISECOND, 0);
		dateWithTime.set(Calendar.SECOND, 0);
		dateWithTime.set(Calendar.MINUTE, 0);
		dateWithTime.set(Calendar.HOUR_OF_DAY, 0);
		return dateWithTime;
	}

	/**
	 * This method converts a date in the default timezone to a Calendar in GMT.
	 *
	 * @param localDate the date which has to be converted into GMT.
	 * @return Calendar containing the GMT date.
	 */
	public static Calendar toGMTCalendar(Date localDate) {
		Calendar gmtCalendar = new GregorianCalendar();
		gmtCalendar.setTimeZone(GMT_ZONE);
		gmtCalendar.setTime(localDate);
		return gmtCalendar;
	}

	/**
	 * This method converts a GMT time String to a Calendar in GMT.
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toGMTCalendar(String date, String format) throws ParseException {
		return toGMTCalendar(date, format, DEFAULT_YEARSTART);
	}

	/**
	 * This method converts a GMT time String to a Calendar in GMT.
	 * @param date
	 * @param format
	 * @param mode
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toGMTCalendar(String date, String format, int mode) throws ParseException {
		Calendar gmtCalendar = Calendar.getInstance(GMT_ZONE);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.set2DigitYearStart(getTwoDigitYearStart(mode));
		formatter.setTimeZone(GMT_ZONE);
		gmtCalendar.setTime(formatter.parse(date));
		return gmtCalendar;
	}

	/**
	 * This method converts a date in the default timezone to a date in GMT.
	 *
	 * @param localDate the date which has to be converted into GMT.
	 * @return Date the GMT date.
	 */
	public static Date toGMTDate(Date localDate) {
		return toDefaultTimeZone(toGMTCalendar(localDate)).getTime();
	}

	/**
	 * This method converts a date in the default timezone to a date in GMT
	 * after discarding the time fields
	 *
	 * @param localDate the date which has to be converted into GMT.
	 * @return Date the GMT date with no time.
	 */
	public static Date toGMTDateOnly(Date localDate) {
		return dateOnly(toGMTDate(localDate));
	}

	/**
	 * This method returns the current date in the default time zone
	 *
	 * @return Date current date
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * This method returns the current date in the default time zone
	 *
	 * @return Date current date
	 */
	public static Calendar getCurrentCalendar() {
		return Calendar.getInstance(DEFAULT_TIMEZONE);
	}

	/**
	 * This method returns the current date in the GMT time zone
	 *
	 * @return Date current date in GMT timezone
	 */
	public static Date getCurrentGMTDate() {
		return toGMTDate(new Date());
	}

	/**
	 * This method returns the current date as a Calendar in the GMT zone
	 *
	 * @return Calendar current date in GMT timezone
	 */
	public static Calendar getCurrentGMTCalendar() {
		return toGMTCalendar(new Date());
	}

	/**
	 * This method converts a date in the default timezone to a Calendar in
	 * any other time zone.
	 *
	 * @param localDate the date which has to be converted.
	 * @param timezone timezone into which the date has to be converted.
	 * @return Calendar the date converted to another time zone.
	 */
	public static Calendar convertTimeZone(Date localDate, String timezone) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone(timezone));
		calendar.setTime(localDate);
		return calendar;
	}

	/**
	 * This method converts a date in any timezone to a date in
	 * any other time zone.
	 *
	 * @param date the date which has to be converted.
	 * @param timezone timezone into which the Calendar has to be converted.
	 * @return Calendar the date converted to another time zone.
	 */
	public static Calendar convertTimeZone(Calendar date, String timezone) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone(timezone));
		calendar.setTimeInMillis(date.getTimeInMillis());
		return calendar;
	}

	/**
	 * This method returns the Timestamp value in GMT corresponding to
	 * the Date passed as argument.
	 *
	 * @param localDate the date in the default time zone.
	 * @return Timestamp gmt timestamp
	 */
	public static Timestamp toGMTTimestamp(Date localDate) {
		return new Timestamp(toDefaultTimeZone(toGMTCalendar(localDate)).getTimeInMillis());
	}

	/**
	 * This method returns the Timestamp value in GMT corresponding to
	 * the Calendar passed as argument.
	 *
	 * @param localDate the date in the default time zone.
	 * @return Timestamp gmt timestamp
	 */
	public static Timestamp toGMTTimestamp(Calendar localDate) {
		return new Timestamp(toDefaultTimeZone(convertTimeZone(localDate, GMT)).getTimeInMillis());
	}

	/**
	 * This method returns the current Timestamp value in GMT
	 *
	 * @return Timestamp gmt timestamp
	 */
	public static Timestamp getCurrentGMTTimestamp() {
		return toGMTTimestamp(new Date());
	}

	/**
	 * This method returns the current time stamp in the default time zone
	 *
	 * @return Timestamp current date
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * This method merges a date field without time and a time value
	 * into a date with time fields.If the date object contains time fields,
	 * that time will be discarded and only the specified time will be appended.
	 * All the dates will be in the default time zone of the jvm
	 *
	 * @param date date without time fields
	 * @param time time to be appended to the date
	 * @return Date merged date and time
	 */
	public static Date mergeDateAndTime(Date date, int time) {

		int days = 0;
		int minutes = 0;
		int hours = 0;
		int currentMinutes = 0;
		int totalTimeInMinutes = 0;
		Calendar calendar = null;
		if (time == 0) {
			return date;
		}
		calendar = new GregorianCalendar();
		calendar.setTime(date);
		currentMinutes = (calendar.get(Calendar.HOUR_OF_DAY) * MINUTES_IN_HOUR) + (calendar.get(Calendar.MINUTE));
		totalTimeInMinutes = currentMinutes + time;
		if (totalTimeInMinutes >= MINUTES_IN_DAY) {
			days = totalTimeInMinutes / MINUTES_IN_DAY;
			date = dayAfter(dateOnly(calendar.getTime()), days);
			minutes = totalTimeInMinutes - (days * MINUTES_IN_DAY);
			hours = minutes / MINUTES_IN_HOUR;
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, hours);
			calendar.set(Calendar.MINUTE, minutes % MINUTES_IN_HOUR);
			return calendar.getTime();
		}
		hours = totalTimeInMinutes / MINUTES_IN_HOUR;
		calendar.setTime(dateOnly(date));
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, totalTimeInMinutes % MINUTES_IN_HOUR);
		return calendar.getTime();
	}

	/**
	 * This method return the date after a specified number of days
	 * All the dates will be in the default timezone of the jvm
	 *
	 * @param date date to which the number of days have to be added.
	 * @param days days - number of days to be added.
	 * @return Date after adding the specified number of days
	 */
	public static Date dayAfter(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * This method return the total value of all time fields in minutes
	 * from a given date in default time zone
	 *
	 * @param date date whose time value is required.
	 * @return int total time in minutes
	 */
	public static int getTimeInMinutes(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return (calendar.get(Calendar.HOUR_OF_DAY) * MINUTES_IN_HOUR) + (calendar.get(Calendar.MINUTE));
	}

	/**
	 * Returns the difference, in milliseconds, between date1 and date2.
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static long delta(Date firstDate, Date secondDate) {
		return secondDate.getTime() - firstDate.getTime();
	}

	/**
	 * Returns the difference, in minutes, between date1 and date2.
	 * @param firstdate
	 * @param secondDate
	 * @return
	 */
	public static int deltaMinutes(Date firstdate, Date secondDate) {
		return (int) (delta(firstdate, secondDate) / MILLIS_IN_MINUTE);
	}

	/**
	* Returns the difference, in milliseconds, between date1 and date2.
	 * @param firstCall
	 * @param secondCall
	 * @return
	 */
	public static long delta(Calendar firstCall, Calendar secondCall) {
		return secondCall.getTimeInMillis() - firstCall.getTimeInMillis();
	}

	/**
	* Returns the difference, in minutes, between date1 and date2.
	* @param firstCall
	* @param secondCAll
	* @return
	*/
	public static long deltaMinutes(Calendar firstCall, Calendar secondCAll) {
		return delta(firstCall, secondCAll) / MILLIS_IN_MINUTE;
	}

	/**
	 * Returns the difference, in days, between date1 and date2.
	 * @param firstCall
	 * @param secondCAll
	 * @return
	 */
	public static long getHourDiff(Calendar firstCall, Calendar secondCAll) {
		Calendar firstCalendar = (Calendar) firstCall.clone();
		Calendar secondCalendar = (Calendar) secondCAll.clone();
		firstCalendar.clear();
		secondCalendar.clear();
		firstCalendar.set(firstCall.get(Calendar.YEAR), firstCall.get(Calendar.MONTH),
			firstCall.get(Calendar.DAY_OF_MONTH), firstCall.get(Calendar.HOUR_OF_DAY), firstCall.get(Calendar.MINUTE),
			firstCall.get(Calendar.SECOND));
		secondCalendar.set(secondCAll.get(Calendar.YEAR), secondCAll.get(Calendar.MONTH),
			secondCAll.get(Calendar.DAY_OF_MONTH), secondCAll.get(Calendar.HOUR_OF_DAY),
			secondCAll.get(Calendar.MINUTE), secondCAll.get(Calendar.SECOND));

		/*
		System.out.println("cal1.getTime()"+cal1.getTime());
		System.out.println("cal2.getTime()"+cal2.getTime());
		System.out.println("calendar1.getTime()"+calendar1.getTime());
		System.out.println("calendar2.getTime()"+calendar2.getTime());*/
		return (secondCalendar.getTime().getTime() - firstCalendar.getTime().getTime()) / MILLIS_IN_HOUR;
	}

	/**
	* Returns the difference, in days, between date1 and date2.
	 * @param firstCall
	 * @param secondCall
	 * @return
	 */
	public static long getDaysDiff(Calendar firstCall, Calendar secondCall) {
		Calendar firstCalendar = (Calendar) firstCall.clone();
		Calendar secondCalendar = (Calendar) secondCall.clone();
		firstCalendar.clear();
		secondCalendar.clear();
		firstCalendar.set(firstCall.get(Calendar.YEAR), firstCall.get(Calendar.MONTH),
			firstCall.get(Calendar.DAY_OF_MONTH));
		secondCalendar.set(secondCall.get(Calendar.YEAR), secondCall.get(Calendar.MONTH),
			secondCall.get(Calendar.DAY_OF_MONTH));

		/*
		System.out.println("cal1.getTime()"+cal1.getTime());
		System.out.println("cal2.getTime()"+cal2.getTime());
		System.out.println("calendar1.getTime()"+calendar1.getTime());
		System.out.println("calendar2.getTime()"+calendar2.getTime());*/
		return (secondCalendar.getTime().getTime() - firstCalendar.getTime().getTime()) / MILLIS_IN_DAY;
	}

	/**
	 * This method formats time in minutes into the format HH:mm
	 * Example:-
	 * If the time in minutes is 930, then the HH:mm format will be 15:30
	 *
	 * @param  minutes
	 * @return String
	 */
	public static String toHHmmFormat(int minutes) {

		int days = 0;
		int hours = 0;
		StringBuffer buffer = new StringBuffer();
		if (minutes >= MINUTES_IN_DAY) {
			days = minutes / MINUTES_IN_DAY;
			minutes = minutes - (days * MINUTES_IN_DAY);
			hours = days * HOURS_IN_DAY;
		}
		hours = hours + (minutes / MINUTES_IN_HOUR);
		minutes = minutes % MINUTES_IN_HOUR;
		if (hours < 10) {
			buffer.append(0);
		}
		buffer.append(hours);
		buffer.append(HOUR_MINUTE_DELIMITER);
		minutes = Math.abs(minutes);
		if (minutes < 10) {
			buffer.append(0);
		}
		buffer.append(minutes);
		return buffer.toString();
	}

	/**
	 * This method converts a date string in the dafault format to a
	 * date object
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateString) throws ParseException {
		return toDate(dateString, NORMAL_DATE_FORMAT);
	}

	/**
	 * This method converts a date string in the dafault format to a
	 * Calendar object
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendar(String dateString) throws ParseException {
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(toDate(dateString, NORMAL_DATE_FORMAT));
		return calendar;
	}

	/**
	 * This method converts a date string in the dafault format to a
	 * Calendar object
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendarWithTime(String dateString) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(toDate(dateString, ADVANCED_DATE_FORMAT));
		return calendar;
	}

	/**
	 * This method converts a date string in a specified format to a
	 * date object
	 * @param dateString
	 * @param format
	 * @param mode
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateString, String format, int mode)  {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.set2DigitYearStart(getTwoDigitYearStart(mode));
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method converts a date string in a specified format to a
	 * date object
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateString, String format) throws ParseException {
		return toDate(dateString, format, DEFAULT_YEARSTART);
	}

	/**
	 * This method converts a date string in a specified format to a
	 * Calendar object
	 * @param dateString
	 * @param format
	 * @param mode
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendar(String dateString, String format, int mode) {
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(toDate(dateString, format, mode));
		return calendar;
	}

	/**
	* This method converts a date string in a specified format to a
	 * Calendar object
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendar(String dateString, String format)  {
		return toCalendar(dateString, format, DEFAULT_YEARSTART);
	}

	/**
	 * This method converts a date object into the default string format
	 *
	 * @param date date object
	 * @return String normal format
	 */
	public static String toNormalString(Date date) {
		DateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		return formatter.format(date);
	}

	/**
	 * This method converts a date object into the specified string format
	 *
	 * @param date date object
	 * @param format specified format
	 * @return String date string
	 */
	public static String toStringFormat(Date date, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * This is the method used for formatting a date in the
	 * string format according to the format specified
	 * @param date
	 * @param sourceFormat
	 * @param destinationFormat
	 * @return
	 * @throws ParseException
	 */
	public String toFormattedDateString(String date, String sourceFormat, String destinationFormat)
			throws ParseException {
		Date tempDate = toDate(date, sourceFormat);
		return toStringFormat(tempDate, destinationFormat);
	}

	/**
	 * This method converts a Calendar object into the specified string format
	 *
	 * @param date date object
	 * @param format specified format
	 * @return String date string
	 */
	public static String toStringFormat(Calendar date, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(getDate(date));
	}

	/**
	 * This method converts a date object in the default time zone to
	 * the GMT string in the default format
	 *
	 * @param localDate date object
	 * @return String GMT date string normal format
	 */
	public static String toGMTString(Date localDate) {
		DateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		formatter.setTimeZone(GMT_ZONE);
		return formatter.format(localDate);
	}

	/**
	 * This method converts a date object in the default time zone to
	 * the GMT string in a specified format
	 *
	 * @param localDate date object
	 * @param format specified format
	 * @return String GMT date string in the specified format
	 */
	public static String toGMTString(Date localDate, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(GMT_ZONE);
		return formatter.format(localDate);
	}

	/**
	 *
	 * @param mode
	 * @return Date
	 */
	public static Date getTwoDigitYearStart(int mode) {
		switch (mode) {
			case DEFAULT_YEARSTART :
				return getDefaulttoDigitYearStart();
			case PAST_YEARSTART :
				return getPasttoDigitYearStart();
			default :
				return getDefaulttoDigitYearStart();
		}
	}

	/**
	 *
	 * @return Date
	 */
	private static Date getDefaulttoDigitYearStart() {
		Calendar cal = getCurrentCalendar();
		// By default go to Jan 1st, 5 years back
		//cal.add(Calendar.YEAR,-5);
		String curYear = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		cal.add(Calendar.YEAR, -(Integer.parseInt(curYear)));
		getFirstDayofYear(cal);
		return getDate(cal);
	}

	/**
	 *
	 * @return Date
	 */
	private static Date getPasttoDigitYearStart() {
		Calendar cal = getCurrentCalendar();
		// By default go to Jan 1st, 5 years back
		cal.add(Calendar.YEAR, -100);
		//cal.add(Calendar.DAY_OF_YEAR ,-1);
		return getDate(cal);
	}

	/**
	 *
	 * @param cal
	 */
	private static void getFirstDayofYear(Calendar cal) {
		cal.set(cal.get(Calendar.YEAR), 0, 0);
	}

	/**
	 * Checks whether the <code>Calendar</code> instances represent the same
	 * date ignoring timezone.
	 *
	 * @param firstDate the first <code>Calendar</code> object
	 * @param secondDate the second <code>Calendar</code> object
	 * @return true if both represent the same date
	 */
	public static boolean isDateSameIgnoringTimezone(Calendar firstDate, Calendar secondDate) {
		if ((firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR))
				&& (firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH))
				&& (firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH))) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Checks whether the <code>Calendar</code> instances represent the same
	 * date & time ignoring timezone.
	 *
	 * @param firstDate the first <code>Calendar</code> object
	 * @param secondDate the second <code>Calendar</code> object
	 * @return true if both represent the same date
	 */
	public static boolean isTimeSameIgnoringTimezone(Calendar firstDate, Calendar secondDate) {
		if (isDateSameIgnoringTimezone(firstDate, secondDate)
				&& (firstDate.get(Calendar.HOUR_OF_DAY) == secondDate.get(Calendar.HOUR_OF_DAY))
				&& (firstDate.get(Calendar.MINUTE) == secondDate.get(Calendar.MINUTE))
				&& (firstDate.get(Calendar.SECOND) == secondDate.get(Calendar.SECOND))) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * checks if the string date is a valid date in format given
	 * @param date as String
	 * @param format with which the string date has to be validated
	 * @return boolean - true if valid date of the
	 * format given, false if invalid date or
	 * invalid format
	 * edited by Vinod S
	 */
	public static boolean isValidDateFormat(String date, String format) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
		ParsePosition position = new ParsePosition(0);
		FieldPosition field = new FieldPosition(0);
		boolean isValid = true;

			Date theDate = dateFormat.parse(date, position);
			if(theDate==null){
				return false;
			}
			buffer = dateFormat.format(theDate, buffer, field);
			if ((buffer.toString()).compareToIgnoreCase(date) != 0)
				isValid = false;

		return isValid;
	}
	/**
	 * This method converts a date in any timezone to a date in the default
	 * time zone of the jvm. This is different from <code>toDefaultTimeZone(
	 * Calendar)</code> which does a copy of certain Calendar fields (YEAR,
	 * MONTH, DAY_OF_MONTH, HOUR_OF_DAY, MINUTE and SECOND).
	 *
	 * @param date the date which has to be converted.
	 * @return Calendar the date converted to another time zone.
	 */
	public static Calendar convertTimeZone(Calendar date) {
		Calendar local = new GregorianCalendar();
		local.setTimeInMillis(date.getTimeInMillis());
		return local;
	}

	/**
	 * This method returns the infinite date in the default timezone.
	 *
	 * @return Date
	 */
	public static Date getInfiniteDate() {
		try {
			return toDate(INFINITE_DATE_FORMAT, ADVANCED_DATE_FORMAT);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method returns the infinite Timestamp in the default timezone.
	 *
	 * @return Date
	 */
	public static Timestamp getInfiniteTimestamp() {
		return new Timestamp(getInfiniteDate().getTime());
	}

	/**
	 * Utility method to get the minutes difference from GMT and
	 * JVM TimeZone with DST
	 * @return
	 */
	public static int getGMTOffset() {
		final int milliSecPerMinute = 1000 * 60;
		TimeZone tzone = TimeZone.getDefault();
		int offset = tzone.getRawOffset();

		// if current time inside daylight period, apply DST offset
		offset = tzone.inDaylightTime(new Date()) ? offset + tzone.getDSTSavings() : offset;

		return offset / milliSecPerMinute;
	}

    /**
     *
      * @return
     */
    public static int getGMTOffsetWithoutDST() {
       final int milliSecPerMinute = 1000 * 60;
		TimeZone tzone = TimeZone.getDefault();
		int offset = tzone.getRawOffset();
		return offset / milliSecPerMinute;
    }

}
