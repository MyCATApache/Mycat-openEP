package io.mycat.ep.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Ice.Object;
import io.mycat.ep.constant.MagicData;
import io.mycat.ep.exception.ProcessInfoException;
import io.mycat.ep.exception.RedirectSystemOutException;
import io.mycat.ep.exception.UnknownRPCServiceExcepton;
import me.jor.util.Help;

public class ProcessInfo {
	private static String PID_TEXT;
	private static int PID;
	private static final String PORT_TEXT=System.getProperty(MagicData.APP_PORT);
	private static int[] PORT;
	private static int APP_BUFSIZE;
	private static String APP_SERVICE_NAME_PREFIX;
	private static Class ANNOTATION_INIT_CLASS;
	private static String GROOVY_CLASS_PATH;
	private static String XML_CLASS_PATH;
	private static String PROPERTIES_CLASS_PATH;
	private static int RING_BUFFER_SIZE;
	private static long CLIENT_REQUEST_WAIT_MILLIS;
	
	static{
		redirectSystemOut();
		extractPort();
		extractPID();
		extractBufSize();
		extractServiceNamePrefix();
		extractAnnotationInitClass();
		extractGroovyClassPath();
		extractXmlClassPath();
		extractPropertiesClassPath();
		extractRingBufSize();
		extractClientRequestWaitMillis();
	}
	private static String[] mainArgs;
	public static void setMainArgs(String[] args){
		mainArgs=args;
	}
	public static String[] getMainArgs(){
		return mainArgs;
	}
	private static void redirectSystemOut(){
		String path=System.getProperty(MagicData.SYSTEM_OUT);
		if(Help.isNotEmpty(path)){
			File out=new File(path);
			if(out.exists()){
				out.renameTo(new File(path+'.'+Help.currentTimeToTxt("yyyyMMddHHmmssSSS")));
				out=new File(path);
			}
			try {
				out.createNewFile();
				System.setOut(new PrintStream(out));
			} catch (IOException e) {
				throw new RedirectSystemOutException(e);
			}
		}
	}
	private static void extractPort(){
		try{
			StringTokenizer tokenizer=new StringTokenizer(PORT_TEXT,MagicData.PORT_SPLITOR);
			List<Integer> ports=new ArrayList<>();
			while(tokenizer.hasMoreTokens()){
				String range=tokenizer.nextToken();
				StringTokenizer rangeTokenizer=new StringTokenizer(range,MagicData.PORT_RANGE_SPLITOR);
				while(rangeTokenizer.hasMoreTokens()){
					String port=rangeTokenizer.nextToken();
					ports.add(Integer.parseInt(port));
				}
			}
			PORT=new int[ports.size()];
			for(int i=0,l=PORT.length;i<l;i++){
				PORT[i]=ports.get(i);
			}
		}catch(Exception e){
			PORT=MagicData.DEFAULT_PORT;
		}
	}
	private static void extractPID(){
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        String name = runtime.getName(); // format: "pid@hostname"  
        try {  
            PID_TEXT=name.substring(0, name.indexOf('@'));  
        } catch (Exception e) {  
            PID_TEXT="-1";  
        }
	}
	private static void extractBufSize(){
		try{
			APP_BUFSIZE=Integer.parseInt(System.getProperty(MagicData.APP_BUFSIZE));
		}catch(Exception e){
			APP_BUFSIZE=MagicData.DEFAULT_APP_BUFSIZE;
		}
	}
	private static void extractAnnotationInitClass(){
		String className=System.getProperty(MagicData.APP_ANNOTATION_INIT_CLASS);
		if(Help.isNotEmpty(className)){
			try {
				ANNOTATION_INIT_CLASS=Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw new ProcessInfoException(e);
			}
		}
	}
	private static void extractGroovyClassPath(){
		GROOVY_CLASS_PATH=System.getProperty(MagicData.APP_GROOVY_CLASS_PATH);
	}
	private static void extractXmlClassPath(){
		XML_CLASS_PATH=System.getProperty(MagicData.APP_XML_CLASS_PATH);
	}
	private static void extractPropertiesClassPath(){
		PROPERTIES_CLASS_PATH=System.getProperty(MagicData.APP_PROPERTIES_CLASS_PATH);
	}
	private static void extractServiceNamePrefix(){
		APP_SERVICE_NAME_PREFIX=System.getProperty(MagicData.APP_SERVICE_NAME_PREFIX);
	}
	private static void extractRingBufSize(){
		try{
			RING_BUFFER_SIZE=Integer.parseInt(System.getProperty(MagicData.APP_RING_BUFFER_SIZE));
		}catch(Exception e){
			RING_BUFFER_SIZE=MagicData.DEFAULT_RING_BUFFER_SIZE;
		}
	}
	private static void extractClientRequestWaitMillis(){
		CLIENT_REQUEST_WAIT_MILLIS=Help.convert(System.getProperty(MagicData.CLIENT_REQUEST_WAIT_MILLIS), MagicData.DEFAULT_CLIENT_REQUEST_WAIT_MILLIS);
	}
	public static String getPortText(){
		return PORT_TEXT;
	}
	public static int[] getPort(){
		return PORT;
	}
	public static String getPIDText(){
		return PID_TEXT;
	}
	public static int getPID(){
		return PID;
	}
	public static int getBufSize(){
		return APP_BUFSIZE;
	}
	public static String getServiceNamePrefix(){
		return APP_SERVICE_NAME_PREFIX;
	}
	public static Class getAnnotationInitClass(){
		return ANNOTATION_INIT_CLASS;
	}
	public static String getGroovyClassPath(){
		return GROOVY_CLASS_PATH;
	}
	public static String getXmlClassPath(){
		return XML_CLASS_PATH;
	}
	public static String getPropertiesClassPath(){
		return PROPERTIES_CLASS_PATH;
	}
	
	public static int getRingBufSize() {
		return RING_BUFFER_SIZE;
	}
	public static StringTokenizer getPipelineInitializerTokens() {
		String names=System.getProperty(MagicData.PIPELINES);
		if(Help.isEmpty(names)){
			return null;
		}
		return new StringTokenizer(names,MagicData.CLASS_NAME_SPILOR);
	}
	public static boolean getUseDefaultPipeline() {
		return Help.convert(System.getProperty(MagicData.USE_DEFAULT_PIPELINE), true);
	}
	public static int getReadIdleSec() {
		return Help.convert(System.getProperty(MagicData.CHANNEL_READ_IDLE_SEC), MagicData.DEFAULT_CHANNEL_IDLE_SEC);
	}
	public static int getWriteIdleSec() {
		return Help.convert(System.getProperty(MagicData.CHANNEL_WRITE_IDLE_SEC), MagicData.DEFAULT_CHANNEL_IDLE_SEC);
	}
	public static int getAllIdleSec() {
		return Help.convert(System.getProperty(MagicData.CHANNEL_READ_WRITE_IDLE_SEC), MagicData.DEFAULT_CHANNEL_IDLE_SEC);
	}
	public static int getQueuedConnectionCount() {
		return Help.convert(System.getProperty(MagicData.QUEUED_CONNECTION_COUNT), MagicData.DEFAULT_QUEUED_CONNECTION_COUNT);
	}
	private static int getWriteBuffer(String bufKey,int defaultValue){
		String bufSize=System.getProperty(bufKey);
		if(Help.isEmpty(bufSize)){
			return defaultValue;
		}else if(bufSize.endsWith("K") || bufSize.endsWith("k")){
			return Integer.parseInt(bufSize.substring(0,bufSize.length()-1))*1024;
		}else if(bufSize.endsWith("M") || bufSize.endsWith("m")){
			return Integer.parseInt(bufSize.substring(0,bufSize.length()-1))*1024*1024;
		}else{
			return Integer.parseInt(bufSize);
		}
	}
	public static int getCellingWriteBuffer() {
		return getWriteBuffer(MagicData.CELLING_WRITE_BUFFER,MagicData.DEFAULT_CELLING_WRITE_BUFFER);
	}
	public static int getFloorWriteBuffer() {
		return getWriteBuffer(MagicData.FLOOR_WRITE_BUFFER, MagicData.DEFAULT_FLOOR_WRITE_BUFFER);
	}
	public static int getMaxChannelBuffer() {
		return Help.convert(System.getProperty(MagicData.MAX_CHANNEL_BUFFER), MagicData.DEFAULT_CHANNEL_BUFFER);
	}
	public static int getMaxIdleChannelPerService() {
		return Help.convert(System.getProperty(MagicData.MAX_IDLE_CHANNEL_PER_SERVICE), MagicData.DEFAULT_CHANNEL_PER_SERVICE);
	}
	public static int getMinIdleChannelPerService() {
		return Help.convert(System.getProperty(MagicData.MIN_IDLE_CHANNEL_PER_SERVICE), MagicData.DEFAULT_CHANNEL_PER_SERVICE);
	}
	public static int getMaxTotalChannelPerService() {
		return Help.convert(System.getProperty(MagicData.MAX_CHANNEL_PER_SERVICE), MagicData.DEFAULT_CHANNEL_PER_SERVICE);
	}
	public static long getMaxWaitChannelMillis() {
		return Help.convert(System.getProperty(MagicData.MAX_CHANNEL_WAIT_MILLIS), MagicData.DEFAULT_MAX_CHANNEL_WAIT_MILLIS);
	}
	public static long getMinEvictableIdleChannelMillis() {
		return Help.convert(System.getProperty(MagicData.MIN_EVICTABLE_IDLE_CHANNEL_MILLIS), MagicData.DEFAULT_MIN_EVICTABLE_IDLE_CHANNEL_MILLIS);
	}
	public static String getClientChannelPoolType() {
		return Help.convert(System.getProperty(MagicData.CLIENT_CHANNEL_POOL_TYPE),MagicData.DEFAULT_CLIENT_CHANNEL_POOL_TYPE);
	}
	
	public static long getClientRequestWaitMillis() {
		return CLIENT_REQUEST_WAIT_MILLIS;
	}
	public static String[] getAppIceInit(){
		String args=System.getProperty(MagicData.APP_ICE_INIT);
		return Help.convert(args, "").split(";");
	}
	public static String getAppIceAdapterName(){
		return System.getProperty(MagicData.APP_ICE_ADAPTER_NAME);
	}
	public static String getAppIcePort(){
		return System.getProperty(MagicData.APP_ICE_PORT);
	}
	public static Class<?> getAppIceInterface() throws ClassNotFoundException{
		return Class.forName(System.getProperty(MagicData.APP_ICE_INTERFACE));
	}
	public static String getAppIceIdentity(){
		return System.getProperty(MagicData.APP_ICE_IDENTITY);
	}
	public static String getAppIceName(){
		return Help.convert(System.getProperty(MagicData.APP_ICE_NAME), "");
	}
	/**
	 * 单位是秒
	 * @return
	 */
	public static long getUserSessionLocalCacheLife() {
		return Help.convert(System.getProperty(MagicData.USER_SESSION_lOCAL_CACHE_LIFE),MagicData.DEFAULT_USER_SESSION_LOCAL_CACHE_LIFE);
	}
	public static long getUserSessionLocalCacheMaxSize() {
		return Help.convert(System.getProperty(MagicData.USER_SESSION_LOCAL_CACHE_MAX_SIZE), MagicData.DEFAULT_USER_SESSION_LOCAL_CACHE_MAX_SIZE);
	}
	public static String getAppIceServiceName() {
		return System.getProperty(MagicData.APP_ICE_SERVICE_NAME);
	}
	public static Class<Ice.Object> getAppIceServiceType() {
		String className=System.getProperty(MagicData.APP_ICE_SERVICE_TYPE);
		if(Help.isNotEmpty(className)){
			try {
				return (Class<Ice.Object>) Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw new UnknownRPCServiceExcepton(e);
			}
		}
		return null;
	}
}
