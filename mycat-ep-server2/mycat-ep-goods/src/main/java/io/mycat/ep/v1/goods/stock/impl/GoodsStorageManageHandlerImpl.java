package io.mycat.ep.v1.goods.stock.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Ice.Current;
import io.mycat.ep.constant.CommonAccessStatus;
import io.mycat.ep.goods.constant.GoodsAccessStatus;
import io.mycat.ep.goods.dao.GoodsDAO;
import io.mycat.ep.goods.exception.GoodsStorageException;
import io.mycat.ep.goods.util.GoodsRedisKeyGenerator;
import io.mycat.ep.v1.goods.stock.StorageAmount;
import io.mycat.ep.v1.goods.stock.StorageChange;
import io.mycat.ep.v1.goods.stock.StorageChangeResult;
import io.mycat.ep.v1.goods.stock.StorageQuery;
import io.mycat.ep.v1.goods.stock._GoodsStorageManageHandlerDisp;
import me.jor.redis.JedisConnection;

public class GoodsStorageManageHandlerImpl extends _GoodsStorageManageHandlerDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8217575993072033441L;
	private static final String CHANGE_STORAGE_LUA;
	static{
		try {
			CHANGE_STORAGE_LUA=chnageStorageLua();
		} catch (IOException e) {
			throw new GoodsStorageException(e);
		}
	}
	public static String chnageStorageLua() throws IOException{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		PrintStream ps=new PrintStream(baos);
		ps.println("local c=redis.call('hincrby',KEYS[1],ARGV[1])");
		ps.println("if (c<0) then");
		ps.println("    redis.call('hincrby',KEYS[1],-ARGV[1])");
		ps.println("    return -1");
		ps.println("else");
		ps.println("    return 1");
		ps.println("end");
		ps.flush();
		ps.close();
		baos.flush();
		return baos.toString();
	}
	
	private JedisConnection redis;
	private GoodsDAO goodsDAO;
	
	public JedisConnection getRedis() {
		return redis;
	}

	public void setRedis(JedisConnection redis) {
		this.redis = redis;
	}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}
	
	
	public StorageChangeResult changeStorage(StorageChange change, Current __current) {
		//本模块应当还有一个接口定义与实现提供加载库存到redis功能，与本接口都属于仓储/库存模块，但是两个接口
		//还有一个异步化多进程的方案，耗时太久就不实现了
		//本实现利用redis单线程特性和redis执行lua脚本的原子性实现
		int result=Integer.parseInt(redis.eval(CHANGE_STORAGE_LUA, 
				new String[]{GoodsRedisKeyGenerator.generateGoodsKey(redis,change.goodsId)}, 
				Integer.toString(change.amount)).toString());//TODO eval在这里究竟返回了什么应该测试一下
		return new StorageChangeResult(result>0?CommonAccessStatus.SUCCESS:GoodsAccessStatus.GOODS_STORAGE_NOT_ENOUGH);
	}

	public StorageAmount queryGoodsAmount(StorageQuery query, Current __current) {
		// TODO Auto-generated method stub
		return null;
	}

	public StorageChangeResult changeStorageBatch(StorageChange[] change, Current __current) {
		StorageChangeResult result=new StorageChangeResult();
		for(int i=0,l=change.length;i<l;i++){
			StorageChangeResult r=changeStorage(change[i],__current);
			result.status&=r.status;
		}
		return result;
	}

}
