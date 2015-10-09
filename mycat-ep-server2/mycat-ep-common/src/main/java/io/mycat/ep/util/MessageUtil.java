package io.mycat.ep.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import me.jor.util.Help;

public class MessageUtil {
	private static final Map<String,Object[]> TEMPLATE_MAP=new ConcurrentHashMap<>();
	
	private static Object[] getPattern(final String template){
		return TEMPLATE_MAP.computeIfAbsent(template, k->{
			List list=new ArrayList();
			boolean param=false;
			int prevStart=0;
			int start=0;
			int l=template.length();
			for(int i=0;i<l;i++){
				char c=template.charAt(i);
				if(param){
					if(c== '}'){
						param=false;
						list.add(Integer.parseInt(template.substring(start,i)));
						prevStart=start=i+1;
					}else if(c<'0' || c>'9'){
						start=prevStart;
					}
				}else if(c=='{'){
					param=true;
					list.add(template.substring(start,i));
					prevStart=start;
					start=i+1;
				}
			}
			if(start<l){
				list.add(template.substring(start));
			}
			return list.toArray();
		});
	}
	
	public static String generateMessage(String template,Object... args){
		if(Help.isEmpty(args)){
			return template;
		}else{
			Object[] t=getPattern(template);
			StringBuilder b=new StringBuilder();
			for(int i=0,l=t.length;i<l;i++){
				Object o=t[i];
				if(o instanceof String){
					b.append(o);
				}else{
					b.append(args[(Integer)o]);
				}
			}
			return b.toString();
		}
	}
	public static void main(String[] args) {
		String msg="{0}aaaa{0}bbbb{1}cccc{2}{1}dddd";
		System.out.println(java.util.Arrays.toString(getPattern(msg)));
		System.out.println(msg);
		System.out.println(generateMessage(msg,"[x]","[y]","[z]"));
	}
}
