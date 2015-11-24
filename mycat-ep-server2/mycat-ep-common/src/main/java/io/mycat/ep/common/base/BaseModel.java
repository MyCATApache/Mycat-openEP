package io.mycat.ep.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> fields;
	
	private String orderBy;
	
	// insert 插入返回主键id
	private Object pk;

	//重写toString方法,转换成json格式
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	
	public Object getPk() {
		return pk;
	}

	public void setPk(Object pk) {
		this.pk = pk;
	}


	public List<String> getFields() {
		return fields;
	}


	public void setFields(List<String> fields){
		List<String> columns=new ArrayList<String>();
		for (String column : fields) {
			String field=toUnderlineCase(column);
			columns.add(field);
		}
		this.fields=columns;
	}
	
	public void setColunms(List<String> columns){
		this.fields=columns;
	}
	

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = toUnderlineCase(orderBy);
	}
	
	private String toUnderlineCase(String camelCaseStr) {
		if (camelCaseStr == null) {
			return null;
		}

		final int length = camelCaseStr.length();
		StringBuilder sb = new StringBuilder();
		char c;
		boolean isPreUpperCase = false;
		for (int i = 0; i < length; i++) {
			c = camelCaseStr.charAt(i);
			boolean isNextUpperCase = true;
			if (i < (length - 1)) {
				isNextUpperCase = Character.isUpperCase(camelCaseStr.charAt(i + 1));
			}
			if (Character.isUpperCase(c)) {
				if (!isPreUpperCase || !isNextUpperCase) {
					if (i > 0) sb.append("_");
				}
				isPreUpperCase = true;
			} else {
				isPreUpperCase = false;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}
}
