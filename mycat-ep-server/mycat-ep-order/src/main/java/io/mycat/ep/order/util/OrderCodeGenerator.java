package io.mycat.ep.order.util;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Date;

import me.jor.util.Help;

public class OrderCodeGenerator {
	public static String generate(long userId){
		return generate(userId,new Date());
	}
	public static String generate(long userId,Date lastLoginTime){
		Instant instant=Instant.ofEpochMilli(lastLoginTime.getTime());
		String year=Integer.toString(instant.get(ChronoField.YEAR)/100);
		String day=Integer.toString(instant.get(ChronoField.DAY_OF_YEAR));
		String sec=Integer.toString(instant.get(ChronoField.SECOND_OF_DAY));
		
		return (year.length()==2?"":"0")+year+//2
				(day.length()==2?"":"0")+day+//2
				(sec.length()==4?"":Help.concat("0", 4-sec.length()))+sec+//4
				userId;
	}
}
