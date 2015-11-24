package io.mycat.ep.constant;

import java.nio.charset.Charset;

public class CharsetSet {
	public static final Charset ISO8859_1=Charset.forName("iso8859-1");
	public static final Charset UTF8=Charset.forName("UTF-8");
	
	public static final Charset MESSAGE_ID_CHARSET=ISO8859_1;
	public static final Charset SERVICE_NAME_CHARSET=ISO8859_1;
}
