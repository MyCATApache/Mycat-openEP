package io.mycat.ep.v1.receiver.impl;

import java.util.List;
import java.util.Map;

import Ice.Current;
import io.mycat.ep.constant.CommonAccessStatus;
import io.mycat.ep.constant.MyCatEPCommonConstant;
import io.mycat.ep.receiver.dao.PurchaseReceiverDAO;
import io.mycat.ep.receiver.exception.PurchaseReceiverException;
import io.mycat.ep.receiver.model.PurchaseReceiverMo;
import io.mycat.ep.receiver.util.ReceiverRedisKeyGenerator;
import io.mycat.ep.v1.receiver.PurchaseReceiver;
import io.mycat.ep.v1.receiver.ReceiverQuery;
import io.mycat.ep.v1.receiver.ReceiverResult;
import io.mycat.ep.v1.receiver._PurchaseReceiverHandlerDisp;
import me.jor.common.GlobalObject;
import me.jor.redis.JedisConnection;
import me.jor.util.Help;

public class PurchaseReceiverHandlerImpl extends _PurchaseReceiverHandlerDisp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5322863495952389206L;

	private JedisConnection redis;
	private PurchaseReceiverDAO purchaseReceiverDAO;
	
	public ReceiverResult queryPurchaseReceivers(ReceiverQuery query, Current __current) {
		try {
			String key=ReceiverRedisKeyGenerator.generatePurchaseReceiverKey(redis,query.userId);
			String json=redis.get(key);
			ReceiverResult result=new ReceiverResult();
			result.status=CommonAccessStatus.SUCCESS;
			if(Help.isEmpty(json)){
				List<PurchaseReceiverMo> list=purchaseReceiverDAO.findPurchaseReceiverListByUserId(query.userId);
				result.receivers=list.toArray(new PurchaseReceiverMo[list.size()]);
					json=GlobalObject.getJsonMapper().writeValueAsString(list);
				redis.setex(key,MyCatEPCommonConstant.getCommonRedisKeyLife(), json);
			}else{
				List list=GlobalObject.getJsonMapper().readValue(json, List.class);
				PurchaseReceiver[] receivers=new PurchaseReceiver[list.size()];
				for(int i=0,l=list.size();i<l;i++){
					PurchaseReceiver receiver=new PurchaseReceiver();
					receivers[i]=receiver;
					Help.populate(receiver,(Map)list.get(i),false);
				}
				result.receivers=receivers;
			}
			return result;
		} catch (Exception e) {
			throw new PurchaseReceiverException(e);
		}
	}

}
