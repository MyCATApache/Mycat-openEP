package io.mycat.ep.constant;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import IceUtilInternal.StringUtil;
import io.mycat.ep.constant.dao.MyCatEPConstantDAO;
import me.jor.util.Help;

public class MyCatEPConstant implements ApplicationContextAware{
	private static MyCatEPConstant instance;
	private MyCatEPConstantDAO myCatEPConstantDAO;
	private Map<String,String> props;
	
	public MyCatEPConstant(){}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		instance=applicationContext.getBean(MyCatEPConstant.class);
	}
	
	public MyCatEPConstantDAO getMyCatEPConstantDAO() {
		return myCatEPConstantDAO;
	}

	public void setMyCatEPConstantDAO(MyCatEPConstantDAO myCatEPConstantDAO) {
		this.myCatEPConstantDAO = myCatEPConstantDAO;
	}

	public void init(){
		//常量参数作为配置中心的一部分，从命令行指定模块功能名，格式为：BUSINESS_GROUP/BUSINESS_LINE/APP/MODULE，以及配置中心的IP:PORT。可以根据这个名字从配置管理中心查到所有配置，将来可以扩展到DataSource、jedis的配置
		//暂时先用数据库代替，将来要改成使用配置中心
		List<Map<String,String>> list=myCatEPConstantDAO.findAll();
		props=new HashMap<String,String>();
		for(int i=0,l=list.size();i<l;i++){
			Map<String,String> map=list.get(i);
			props.put(map.get("key"), map.get("value"));
		}
		props=Collections.unmodifiableMap(props);
	}
	public static MyCatEPConstant getInstance(){
		return instance;
	}
	
	public String getValue(String name){
		return props.get(name);
	}
	public String getStringValue(String name,String defaultValue){
		return Help.convert(getValue(name), defaultValue);
	}
	public int getIntValue(String name,int defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return Integer.parseInt(v);
	}
	public long getLongValue(String name,long defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return Long.parseLong(v);
	}
	public double getDoubleValue(String name,double defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return Double.parseDouble(v);
	}
	public BigInteger getBigIntegerValue(String name,BigInteger defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return new BigInteger(v);
	}
	public BigDecimal getBigDecimal(String name,BigDecimal defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return new BigDecimal(v);
	}
	public boolean getBoolean(String name,boolean defaultValue){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaultValue;
		}
		return Boolean.parseBoolean(v);
	}
	public String[] getStringValues(String name,String splitor,String... defaults){
		String v=getValue(name);
		if(Help.isEmpty(v)){
			return defaults;
		}
		return StringUtil.splitString(v, splitor);
	}
	private static final Pattern MSG_ARGS=Pattern.compile("\\{\\d+\\}");
	public String getMsg(String name,Object... args){
		String[] parts=MSG_ARGS.split(getValue(name));
		String[] result=new String[args.length+parts.length];
		for(int i=0,il=parts.length,j=0,jl=args.length,c=0,l=result.length;c<l;){
			if(i<il){
				if(i==0 && "".equals(parts[i])){
					result[c++]=args[j++].toString();
					i++;
					continue;
				}else{
					result[c++]=parts[i++];
				}
			}
			if(j<jl){
				result[c++]=args[j++].toString();
			}
		}
		return Help.concat(result, "");
	}
	public <T extends Enum<T>> T getEnumValue(String name,Class<T> enumType){
		return Enum.valueOf((Class<T>) enumType, name);
	}
	public <T> Class<T> getClass(String name) throws ClassNotFoundException{
		return (Class<T>) Class.forName(getValue(name));
	}
	public <T> T getObject(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return (T) getClass(name).newInstance();
	}
	public <T> T getObject(String name, Object... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<T> c=getClass(name);
		Class[] argTypes=new Class[args.length];
		for(int i=0,l=args.length;i<l;i++){
			argTypes[i]=args[i].getClass();
		}
		return c.getConstructor(argTypes).newInstance(args);
	}

	
}
