package com.mysoft.time;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TimeTest {

	public static void main(String[] args) {
//		创建新的日期对象
		LocalDate today = LocalDate.parse("2014-02-27"); // or LocalDate bday = LocalDate.of(2014,3,18);

/**
 * 		添加日期方法：LocalDate.plusDays，LocalDate.plusWeeks，LocalDate.plusMonths LocalDate.plusYears。
 * 		还有一个通用的LocalDate.plus方法，可以通过TemporalUnit类型指定要添加多少和时间单位
 */
		LocalDate oneMonthFromNow = today.plusDays(30);
	    System.out.println(oneMonthFromNow.isEqual(LocalDate.parse("2014-03-29")));
	 
	    LocalDate nextMonth = today.plusMonths(1);
	    System.out.println(nextMonth.isEqual(LocalDate.parse("2014-03-27")));
	 
	    LocalDate future = today.plus(4, ChronoUnit.WEEKS);
	    System.out.println(future.isEqual(LocalDate.parse("2014-03-27")));
		
	    
/**
 * 		减少日期方法：LocalDate.minusDays，LocalDate.minusMonths
 */
        System.out.println(today.minusWeeks(1).isEqual(LocalDate.parse("2014-02-20")));
        System.out.println(today.minusMonths(2).isEqual(LocalDate.parse("2013-12-27")));
        System.out.println(today.minusYears(4).isEqual(LocalDate.parse("2010-02-27")));
        //减少两个月
        Period twoMonths = Period.ofMonths(2);
        System.out.println(today.minus(twoMonths).isEqual(LocalDate.parse("2013-12-27")));
        
        
/**
 * 		确定日期之间的差（天数，周数，月数或年数）：LocalDate.until
 */
        
        LocalDate vacationStart = LocalDate.parse("2014-07-04");
        //获取Period对象，表示两个日期之间的差对象
        Period timeUntilVacation = today.until(vacationStart);
        System.out.println(timeUntilVacation.getMonths()==(4));
        System.out.println(timeUntilVacation.getDays()==(7));
        
        //获取两个日期之间的天数差
        System.out.println(today.until(vacationStart, ChronoUnit.DAYS)==(127L));
        LocalDate libraryBookDue = LocalDate.parse("2000-03-18");
        
        //比较today是否大于libraryBookDue
        System.out.println(today.until(libraryBookDue).isNegative()==(true));
        System.out.println(today.until(libraryBookDue, ChronoUnit.DAYS)==(-5094L));
        
        LocalDate christmas = LocalDate.parse("2014-12-25");
        System.out.println(today.until(christmas, ChronoUnit.DAYS)==(301L));
        
        
/**
 *      Clock   
 */
        
		final Clock clock = Clock.systemUTC();
		System.out.println( clock.instant() );
		//获取毫秒数
		System.out.println( clock.millis() );
		
		//只获取日期
		final LocalDate date = LocalDate.now();
		System.out.println( date );
		         
		//只获取时间
		final LocalTime time = LocalTime.now();
		System.out.println( time );

		
/**
 * 		使用Duration计算两个日期之间的差
 */
		final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
		final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );
		 
		final Duration duration = Duration.between( from, to );
		System.out.println( "Duration in days: " + duration.toDays() );
		System.out.println( "Duration in hours: " + duration.toHours() );

	}
}
