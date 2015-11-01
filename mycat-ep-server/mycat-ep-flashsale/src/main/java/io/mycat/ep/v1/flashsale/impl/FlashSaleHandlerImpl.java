package io.mycat.ep.v1.flashsale.impl;

import java.util.Date;

import Ice.Current;
import io.mycat.ep.flashsale.constant.FlashSaleAccessStatus;
import io.mycat.ep.flashsale.dao.FlashSaleDAO;
import io.mycat.ep.flashsale.exception.FlashSaleException;
import io.mycat.ep.flashsale.model.vo.FlashSaleResultVo;
import io.mycat.ep.flashsale.util.FlashSaleRedisKeyGenerator;
import io.mycat.ep.v1.flashsale.FlashSaleInfo;
import io.mycat.ep.v1.flashsale.FlashSaleResult;
import io.mycat.ep.v1.flashsale._FlashBuyHandlerDisp;
import me.jor.common.GlobalObject;
import me.jor.redis.JedisConnection;
import me.jor.util.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashSaleHandlerImpl extends _FlashBuyHandlerDisp {

	private static final long serialVersionUID = 4008588744751821265L;
	@Autowired
	private JedisConnection redis;
	@Autowired
	private FlashSaleDAO flashSaleDAO;

    @Override
	public FlashSaleResult flashBuy(FlashSaleInfo sale, Current __current) {//整个逻辑都应该异步化，FLASH_SALES_Q应该分散到不同的REDIS实例
		String json=redis.rpop(FlashSaleRedisKeyGenerator.generateFlashSaleQueue(redis,sale.goodsId));
		//本模块应当提供一个加载抢购数据到redis的接口，供后台管理调用预先把flash_sales加载到redis，这部分以后实现
		//而且这个加载功能应该与flashBuy属于不同的interface定义，但是仍然属于flashsale模块
		if(Help.isEmpty(json)){
			return new FlashSaleResultVo(FlashSaleAccessStatus.FLASH_SALES_EMPTY);
		}
		try {
			FlashSaleResult result=GlobalObject.getJsonMapper().readValue(json, FlashSaleResult.class);
			result.status=1;
			GlobalObject.getExecutorService().submit(()->{
				flashSaleDAO.updateFlashSaleUserId(sale.userId,new Date(),result.flashSaleId);
			});
			return result;
		} catch (Exception e) {
			throw new FlashSaleException(e);
		}
	}

}
