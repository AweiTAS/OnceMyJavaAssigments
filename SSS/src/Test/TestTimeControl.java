package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import Operation.TimeControl;

class TestTimeControl {

	@Test
	void test() throws ParseException {
		//yyyy-MM-dd HH:mm:ss
		assertEquals(600,TimeControl.calUsage("2019-04-09 23:50:01", "2019-04-10 00:00:01"));
		assertEquals(10,TimeControl.calUsage("2019-04-10 21:01:11", "2019-04-10 21:01:21"));
		assertEquals(60,TimeControl.calUsage("2019-04-09 11:01:11", "2019-04-09 11:02:11"));
		
		//
		assertEquals(false,TimeControl.calThisTime(100));
		assertEquals(true,TimeControl.calThisTime(1900));
		
		//
		assertEquals(true,TimeControl.calDay(10000));
		assertEquals(false,TimeControl.calDay(100));
		
		//
		assertEquals("2019-04-11 12:12:34",TimeControl.stampToDate("1554955954000"));
		assertEquals("2019-05-08 09:10:29",TimeControl.stampToDate("1557277829000"));
		
       //
		assertEquals("1554955954000",TimeControl.dateToStamp("2019-04-11 12:12:34"));
		assertEquals("1557277829000",TimeControl.dateToStamp("2019-05-08 09:10:29"));
		
		assertEquals(1557277829000L,TimeControl.dateToStampLong("2019-05-08 09:10:29"));
		assertEquals(1554955954000L,TimeControl.dateToStampLong("2019-04-11 12:12:34"));
		
		//System.out.println(TimeControl.getFirstDayOfWeek("2019-04-09"));
				assertEquals("2019-04-08-00-00",TimeControl.getFirstDayOfWeek("2019-04-09-00-00"));
				assertEquals("2019-04-10",TimeControl.getDayOfWeek("2019-04-09",2));
		
		
		//
		assertEquals("2 h 0 min 0 second",TimeControl.transTime(7200));
		assertEquals("2 h 30 min 30 second",TimeControl.transTime(9030));
		
		//
		assertEquals(1,TimeControl.calHour(3600));
		assertEquals(2,TimeControl.calHour(7200));
		
		//
		assertEquals(1,TimeControl.calMin(3660));
		assertEquals(2,TimeControl.calMin(7320));
		
		
		
		//
		assertEquals(0,TimeControl.calSed(3600));
		assertEquals(6,TimeControl.calSed(7206));
		
		
		
		
		
		
		
		
	}

}