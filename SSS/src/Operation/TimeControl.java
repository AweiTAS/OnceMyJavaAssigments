package Operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//function related to time
public class TimeControl {
	/**
	 * calculate usage between two times
	 * 
	 * @param borrow
	 * @param back
	 * @return time
	 * @throws ParseException
	 */
	public static long calUsage(String borrow, String back) throws ParseException {

		long borrowLong = dateToStampLong(borrow);
		long backLong = dateToStampLong(back);
		long time = (backLong - borrowLong) / 1000;

		return time;
	}

	/**
	 * calculate this time riding
	 * 
	 * @param time
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean calThisTime(long time) throws ParseException {
		long limitTime = 1 * 60;
		if (limitTime < time) {
			return true;
		}
		return false;
	}

	/**
	 * calculate this day usage
	 * 
	 * @param time
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean calDay(long time) throws ParseException {
		long limitTime = 120 * 60;
		if (limitTime < time) {
			return true;
		}
		return false;
	}

	/**
	 * translate the date to stamplong
	 *
	 * @param time
	 * @return ts
	 * @throws ParseException
	 */
	public static long dateToStampLong(String time) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(time);
		long ts = date.getTime();
		return ts;
	}

	/**
	 * translate the date to stampString
	 * 
	 * @param time
	 * @return res
	 * @throws ParseException
	 */
	public static String dateToStamp(String time) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(time);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/**
	 * translate the stampString to date
	 * 
	 * @param time
	 * @return res
	 * @throws ParseException
	 */
	public static String stampToDate(String time) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(time);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * get first day of the week
	 * 
	 * @param date
	 * @return res
	 * @throws ParseException
	 */
	public static String getFirstDayOfWeek(String s) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date date = simpleDateFormat.parse(s);

		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		String res = simpleDateFormat.format(c.getTime());
		return res;
	}

	/**
	 * get day of the week
	 * 
	 * @param date
	 * @return res
	 * @throws ParseException
	 */
	public static String getDayOfWeek(String s, int day) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(s);

		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + day); // Sunday
		String res = simpleDateFormat.format(c.getTime());
		return res;
	}
	
	/**
	 * get day of the week
	 * 
	 * @param s,day
	 * @return res
	 * @throws ParseException
	 */
	public static String getLastTimeInterval(String s, int day) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(s);

		Calendar calendar1 = new GregorianCalendar();
		calendar1.setFirstDayOfWeek(Calendar.MONDAY);
		calendar1.setTime(date);

		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = day - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		String lastDate = simpleDateFormat.format(calendar1.getTime());
		return lastDate;
	}

	/**
	 * hour-min-second translate the time to hour-min-second
	 * 
	 * @param time
	 * @return String
	 * @throws ParseException
	 */
	public static String transTime(long time) {
		long hour = TimeControl.calHour(time);
		long min = TimeControl.calMin(time);
		long sed = TimeControl.calSed(time);
		return String.valueOf(hour) + " h " + String.valueOf(min) + " min " + String.valueOf(sed) + " second";
	}

	/**
	 * calculate the hour time
	 * 
	 * @param time
	 * @return hours
	 */
	public static long calHour(long time) {
		long hours = (long) (time / 3600);
		return hours;
	}

	/**
	 * calculate the min time
	 * 
	 * @param time
	 * @return minute
	 */
	public static long calMin(long time) {
		time = time - 3600 * calHour(time);
		long minute = (long) (time / 60);
		return minute;
	}

	/**
	 * calculate the second time
	 * 
	 * @param time
	 * @return second
	 */
	public static long calSed(long time) {
		long second = time % 60;
		return second;
	}
}
