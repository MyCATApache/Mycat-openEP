package io.mycat.ep.constant;

public class MagicData {
	/**
	 * java standard output
	 */
	public static final String SYSTEM_OUT = "app.system.out";
	/**
	 * default channel idle seconds 
	 */
	public static final int DEFAULT_CHANNEL_IDLE_SEC = 0;
	/**
	 * empty byte array
	 */
	public static final byte[] EMPTY_BYTE_ARRAY=new byte[0];
	/**
	 * mask code for populating message type
	 */
	public static final byte MESSAGE_TYPE_POPULATION_MASK=(byte)0x0f;
	/**
	 * mask code for extracting message type
	 */
	public static final int MESSAGE_TYPE_EXTRACTION_MASK=0xf0;
	/**
	 * mask code for dup flag
	 */
	public static final int MESSAGE_DUP_FLAG_MASK=1<<3;
	/**
	 * mask code for retain
	 */
	public static final int MESSAGE_RETAIN_MASK=1;
	
	public static final int MESSAGE_QOS_LEVEL_MASK=0b00000110;
	/**
	 * class name splitor
	 */
	public static final String CLASS_NAME_SPILOR = ";";
	/**
	 * port splitor 1980,1981,1982 means port 1980 1981 and 1982
	 */
	public static final String PORT_SPLITOR=",";
	/**
	 * port range splitor, 1980-1989 means from port 1980 to 1989
	 */
	public static final String PORT_RANGE_SPLITOR="-";
	/**
	 * default port
	 */
	public static final int[] DEFAULT_PORT={1981};
	/**
	 * jvm system property key, to store listening port
	 */
	public static final String APP_PORT="app.port";
	/**
	 * default buffer size for serialize
	 */
	public static final int DEFAULT_APP_BUFSIZE=512;
	/**
	 * default ring buffer size for disruptor
	 */
	public static final int DEFAULT_RING_BUFFER_SIZE=1024;
	/**
	 * buffer size for serialize
	 */
	public static final String APP_BUFSIZE="app.bufsize";
	/**
	 * prefix of service interface package path 
	 */
	public static final String APP_SERVICE_NAME_PREFIX="app.service.name.prefix";
	/**
	 * mapped to a class name in System.properties which is annotated by {@link org.springframework.context.annotation.ComponentScan}
	 */
	public static final String APP_ANNOTATION_INIT_CLASS="app.annotation.init.class";
	/**
	 * mapped to groovy class path in System.properties for groovy scripts which are configuration files of spring 
	 */
	public static final String APP_GROOVY_CLASS_PATH="app.groovy.class.path";
	/**
	 * mapped to xml class path in System.properties for xml files which are configuration files of spring
	 */
	public static final String APP_XML_CLASS_PATH="app.xml.class.path";
	/**
	 * mapped to properties class path in System.properties for properties files which are configuration files of spring
	 */
	public static final String APP_PROPERTIES_CLASS_PATH = "app.properties.class.path";
	/**
	 * ring buffer size for disruptor, it must be 2n size
	 */
	public static final String APP_RING_BUFFER_SIZE="app.ring.buffer.size";
	/**
	 * class names to initialize netty pipeline, spilited by MagicData.CLASS_NAME_SPILITOR, how many initializer classes, how many pipeline
	 */
	public static final String PIPELINES = "app.pipelines";
	/**
	 * to use default pipeline or not
	 */
	public static final String USE_DEFAULT_PIPELINE = "app.use.default.pipeline";
	/**
	 * channel read idle seconds 
	 */
	public static final String CHANNEL_READ_IDLE_SEC = "app.channel.read.idle.sec";
	/**
	 * channel write idle seconds
	 */
	public static final String CHANNEL_WRITE_IDLE_SEC = "app.channel.write.idle.sec";
	/**
	 * channel read or write idle seconds
	 */
	public static final String CHANNEL_READ_WRITE_IDLE_SEC = "app.channel.read.write.idle.sec";
	/**
	 * SO_BACKLOG size, queued connections before being handled
	 */
	public static final String QUEUED_CONNECTION_COUNT = "app.queued.connection.count";
	/**
	 * default SO_BACKLOG
	 * @see MagicData.QUEUED_CONNECTION_COUNT
	 */
	public static final int DEFAULT_QUEUED_CONNECTION_COUNT = 100;
	/**
	 * value of WRITE_BUFFER_HIGH_WATER_MARK, toplimit or celling of write buffer. it could end with K k M m.<br/> 
	 * gap between celling and floor would better be not too large.<br/>
	 * these two values in both server and client sides would better be the same.
	 */
	public static final String CELLING_WRITE_BUFFER = "app.celling.write.buffer";
	/**
	 * default value of CELLING_WRITE_BUFFER
	 */
	public static final int DEFAULT_CELLING_WRITE_BUFFER = 32*1024;
	/**
	 * value of WRITE_BUFFER_LOW_WATER_MARK, lower limit or floor of write buffer.it could end with K k M m.<br/> 
	 * gap between celling and floor would better be not too large.<br/>
	 * these two values in both server and client sides would better be the same.
	 */
	public static final String FLOOR_WRITE_BUFFER = "app.floor.write.buffer";
	/**
	 * default value of FLOOR_WRITE_BUFFER
	 */
	public static final int DEFAULT_FLOOR_WRITE_BUFFER = 8*1024;
	/**
	 * max channel frame length for netty
	 */
	public static final String MAX_CHANNEL_BUFFER = "app.max.channel.buffer";
	/**
	 * default channel frame length for netty
	 */
	public static final int DEFAULT_CHANNEL_BUFFER=256;
	/**
	 * max idle channel per service in client. it is max idle per key for org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 * @see org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 */
	public static final String MAX_IDLE_CHANNEL_PER_SERVICE = "app.max.idle.channel.per.service";
	/**
	 * min idle channel per service in client. it is min idle per key for org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 * @see org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 */
	public static final String MIN_IDLE_CHANNEL_PER_SERVICE = "app.min.idle.channel.per.service";
	/**
	 * max total channel per service. it is max total per key for org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 * @see org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 */
	public static final String MAX_CHANNEL_PER_SERVICE = "app.max.channel.per.service";
	/**
	 * default max idle channel per service in client
	 */
	public static final int DEFAULT_CHANNEL_PER_SERVICE = 1;
	/**
	 * max channel wait millis. it is max wait millis for org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 * @see org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 */
	public static final String MAX_CHANNEL_WAIT_MILLIS = "app.max.channel.millis";
	/**
	 * default max channel wait millis.
	 */
	public static final long DEFAULT_MAX_CHANNEL_WAIT_MILLIS = 1000;
	/**
	 * min evictable idle channel millis. it is min evictable idle time millis for org.apache.commons.pool2.impl.GenericKeyedObjectPool
	 */
	public static final String MIN_EVICTABLE_IDLE_CHANNEL_MILLIS = "app.min.evictable.idle.channel.millis";
	public static final long DEFAULT_MIN_EVICTABLE_IDLE_CHANNEL_MILLIS = 30*1000;
	/**
	 * @see me.jor.net.rpc.pool.ClientChannelPoolType
	 */
	public static final String CLIENT_CHANNEL_POOL_TYPE = "app.client.channel.pool.type";
	public static final String DEFAULT_CLIENT_CHANNEL_POOL_TYPE = "MULTI";
	public static final String CLIENT_REQUEST_WAIT_MILLIS = "app.client.request.wait.millis";
	public static final long DEFAULT_CLIENT_REQUEST_WAIT_MILLIS = 1000;
	/**
	 * remote service register name
	 */
	public static final String REMOTE_SERVICE_REGISTER = "app.remote.service.register";
	/**
	 * 程序启动时在-D参数中指定
	 */
	public static final String APP_ICE_INIT = "app.ice.init";
	public static final String APP_ICE_ADAPTER_NAME="app.ice.adapter.name";
	public static final String APP_ICE_PORT = "app.ice.port";
	public static final String APP_ICE_INTERFACE = "app.ice.interface";
	public static final String APP_ICE_IDENTITY = "app.ice.identity";
	public static final String APP_ICE_IDENTITY_SPLITOR = "app.ice.identity.splitor";
	public static final String APP_ICE_NAME = "app.ice.name";
	public static final String APP_ICE_USER_SESSION_SERVICE = "app.ice.user.session.service";
	public static final String USER_SESSION_lOCAL_CACHE_LIFE = "app.user.session.local.cache.life";
	public static final long DEFAULT_USER_SESSION_LOCAL_CACHE_LIFE = 5*60;
	public static final String USER_SESSION_LOCAL_CACHE_MAX_SIZE = "app.user.session.local.cache.max.size";
	public static final long DEFAULT_USER_SESSION_LOCAL_CACHE_MAX_SIZE = 10_0000;
	public static final String APP_ICE_SERVICE_NAME = "app.ice.service.name";
	public static final String APP_ICE_SERVICE_TYPE = "app.ice.service.type";
}
