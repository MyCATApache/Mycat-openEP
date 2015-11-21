package io.mycat.ep.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import me.jor.exception.LocalhostException;
import me.jor.util.Help;

public class Localhost {
	private static byte[] localIpBytes;
	private static String localIpStr;
	private static byte[] localMacBytes;
	private static long localMacLong;
	private static String localMacStr;
	private static String localHostName;
	
	public static InetAddress getLocalhost(){
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new LocalhostException(e);
		}
	}
	public static byte[] getLocalIpInBytes(){
		if(Help.isEmpty(localIpBytes)){
			synchronized(Localhost.class){
				if(Help.isEmpty(localIpBytes)){
					localIpBytes=getLocalhost().getAddress();
				}
			}
		}
		return localIpBytes;
	}
	public static String getLocalIpInString(){
		if(Help.isEmpty(localIpStr)){
			synchronized(Localhost.class){
				if(Help.isEmpty(localIpStr)){
					localIpStr=getLocalhost().getHostAddress();
				}
			}
		}
		return localIpStr;
	}
	public static NetworkInterface getNetworkInterface(){
		try {
			return NetworkInterface.getByInetAddress(getLocalhost());
		} catch (SocketException e) {
			throw new LocalhostException(e);
		}
	}
	public static byte[] getLocalMacInBytes(){
		if(Help.isEmpty(localMacBytes)){
			synchronized(Localhost.class){
				if(Help.isEmpty(localMacBytes)){
					try {
						localMacBytes=getNetworkInterface().getHardwareAddress();
					} catch (SocketException e) {
						throw new LocalhostException(e);
					}
				}
			}
		}
		return localMacBytes;
	}
	public static long getLocalMacLong(){
		if(localMacLong==0){
			synchronized(Localhost.class){
				if(localMacLong==0){
					long mac=0;
					getLocalMacInBytes();
					for(int i=0,l=localMacBytes.length;i<l;i++){
						mac=(mac<<8)|localMacBytes[i];
					}
				}
			}
		}
		return localMacLong;
	}
	public static String getLocalMacInString(){
		if(Help.isEmpty(localMacStr)){
			synchronized(Localhost.class){
				if(Help.isEmpty(localMacStr)){
					localMacStr=Long.toString(getLocalMacLong(),16);
				}
			}
		}
		return localMacStr;
	}
	public static String getLocalHostName(){
		if(Help.isEmpty(localHostName)){
			synchronized(Localhost.class){
				if(Help.isEmpty(localHostName)){
					localHostName=getLocalhost().getHostName();
				}
			}
		}
		return localHostName;
	}
	public static void main(String[] args) throws IOException {
		byte[] magic=new byte[]{(byte)0b10100101,(byte)0b10010110,(byte)0b11000011,(byte)0b11110000,
		                         (byte)0b00001111,(byte)0b00111100,(byte)0b01100110,(byte)0b01011010};
		System.out.println(new String(magic,"iso8859-1"));
		long m=0;
		for(byte b:magic){
			m=(m<<8)|b;
		}
		System.out.println(m);
		
		magic=new byte[]{(byte)0b10101010,(byte)0b10101010,(byte)0b10101010,(byte)0b10101010,
				          (byte)0b10101010,(byte)0b10101010,(byte)0b10101010,(byte)0b10101010};
		System.out.println(new String(magic,"ascii"));
		System.out.println(new String(magic,"utf8"));
		System.out.println(new String(magic,"iso8859-1"));
		m=0;
		for(byte b:magic){
			m=(m<<8)|b;
		}
		System.out.println(m);
	}
}
