package io.mycat.ep.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 支持分页 
 * Arrays.asList("id","userName")
 * @author 吴玉 
 *
 * @param <Q>
 */
public class BaseExample<Q> implements Serializable {

	private static final long serialVersionUID = 99999L;

	protected String orderByClause;

	protected boolean distinct;

	protected List<Q> oredCriteria;

	public final String UNDERLINE = "_";

	List<String> fields;

	List<Integer> limit;

	public void setFields(List<String> fields) {
		List<String> columns = new ArrayList<String>();
		for (String column : fields) {
			String field = toUnderlineCase(column);
			columns.add(field);
		}
		this.fields = columns;
	}

	public void setColunms(List<String> columns) {
		this.fields = columns;
	}

	public String toUnderlineCase(String camelCaseStr) {
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
				isNextUpperCase = Character.isUpperCase(camelCaseStr
						.charAt(i + 1));
			}
			if (Character.isUpperCase(c)) {
				if (!isPreUpperCase || !isNextUpperCase) {
					if (i > 0)
						sb.append(UNDERLINE);
				}
				isPreUpperCase = true;
			} else {
				isPreUpperCase = false;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	public List<Integer> getLimit() {
		return limit;
	}

	public void setLimit(List<Integer> limit) {
		this.limit = limit;
	}
}
