package io.mycat.ep.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

import io.mycat.ep.constant.CharsetSet;
import me.jor.util.Help;

public class AccessIdUtil {
	private static final ThreadLocal<LongAdder> increment=new ThreadLocal<LongAdder>();
	
	private static LongAdder getIncrement(){
		LongAdder adder=increment.get();
		if(adder==null){
			adder=new LongAdder();
			increment.set(adder);
		}
		return adder;
	}
	private static String serialNum(){
		LongAdder adder=getIncrement();
		try{
			return Long.toString(adder.longValue(),36);
		}finally{
			adder.increment();
		}
	}
	private static String currentTimeIn36Radix(){
		Calendar now=Calendar.getInstance();
		String y=Integer.toString(now.get(Calendar.YEAR)%100, 36);
		String m=Integer.toString(now.get(Calendar.MINUTE));
		String s=Integer.toString(now.get(Calendar.SECOND));
		String ms=Integer.toString(now.get(Calendar.MILLISECOND));
		return (y.length()==2?y:(0+y))+
				Integer.toString(now.get(Calendar.MONTH),36)+
				Integer.toString(now.get(Calendar.DATE),36)+
				Integer.toString(now.get(Calendar.HOUR_OF_DAY),36)+
				(m.length()==2?m:(0+m))+
				(s.length()==2?s:(0+s))+
				(ms.length()==2?ms:(0+ms));
	}
	
	public static Date parseDateFromMsgId(String msgId){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(msgId.substring(0,2),36));
		calendar.set(Calendar.MONTH, Integer.parseInt(msgId.substring(2,3),36));
		calendar.set(Calendar.DATE, Integer.parseInt(msgId.substring(3,4),36));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(msgId.substring(4,5),36));
		calendar.set(Calendar.MINUTE, Integer.parseInt(msgId.substring(5,7),36));
		calendar.set(Calendar.SECOND, Integer.parseInt(msgId.substring(7,9),36));
		calendar.set(Calendar.MILLISECOND, Integer.parseInt(msgId.substring(9,11),36));
		return calendar.getTime();
	}
	
	public static long parseMillisFromMsgId(String msgId){
		return parseDateFromMsgId(msgId).getTime();
	}
	/**
	 * 由当前时间-设备ID-端口号或进程ID,优先使用端口号-线程ID-序列号构成.
	 * generated currenttime-deviceid-portORprocessID,port has higher priority-threadid-serianum-suffix
	 * @param suffix
	 * @return
	 */
	public static String createMessageId(String suffix){
		if(suffix==null){
			suffix="";
		}
		return currentTimeIn36Radix()+'-'+Localhost.getLocalHostName()+'-'+Help.convert(ProcessInfo.getPortText(), ProcessInfo.getPIDText())+'-'+
				Thread.currentThread().getId()+'-'+serialNum()+'-'+suffix;
	}
	public static byte[] createMessageIdBytes(String suffix){
		return messageIdToBytes(createMessageId(suffix));
	}
	public static String renewMessageId(byte[] bytes){
		return new String(bytes,CharsetSet.MESSAGE_ID_CHARSET);
	}
	public static String extractSuffix(String messageId){
		int suffixStart=messageId.lastIndexOf('-')+1;
		if(suffixStart<messageId.length()){
			return messageId.substring(suffixStart);
		}
		return "";
	}
	public static byte[] messageIdToBytes(String messageId){
		return messageId.getBytes(CharsetSet.MESSAGE_ID_CHARSET);
	}
}
