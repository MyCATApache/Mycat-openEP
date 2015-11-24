package io.mycat.ep.v1.order.impl;

import java.math.BigDecimal;

import Ice.Current;
import io.mycat.ep.ice.proxy.IceProxy;
import io.mycat.ep.order.constant.OrderConstant;
import io.mycat.ep.order.dao.OrderDAO;
import io.mycat.ep.order.model.PurchaseOrder;
import io.mycat.ep.order.model.PurchaseOrderGoods;
import io.mycat.ep.order.util.OrderCodeGenerator;
import io.mycat.ep.v1.goods.GoodsHandlerPrxHelper;
import io.mycat.ep.v1.goods.GoodsPriceQuery;
import io.mycat.ep.v1.goods.GoodsPriceQueryResult;
import io.mycat.ep.v1.goods.stock.GoodsStorageManageHandlerPrxHelper;
import io.mycat.ep.v1.goods.stock.StorageChange;
import io.mycat.ep.v1.order.CartOrderInfo;
import io.mycat.ep.v1.order.OrderInfo;
import io.mycat.ep.v1.order.OrderResult;
import io.mycat.ep.v1.order.OrderedGoods;
import io.mycat.ep.v1.order._OrderHandlerDisp;

public class OrderHandlerImpl extends _OrderHandlerDisp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2877940073174912574L;
	private OrderDAO orderDAO;
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderResult makeOrder(OrderInfo info, Current __current) {
		PurchaseOrder order=new PurchaseOrder(info);
		order.setOrderCode(OrderCodeGenerator.generate(info.userId));
		orderDAO.saveOrder(order);
		long orderId=order.getId();
		OrderedGoods[] ordered=info.orderedGoods;
		long[] goodsIds=new long[ordered.length];
		for(int i=0,l=goodsIds.length;i<l;i++){
			goodsIds[i]=ordered[i].goodsId;
		}
		GoodsPriceQueryResult price=IceProxy.init(GoodsHandlerPrxHelper.class, System.getProperty(OrderConstant.GOODS_PRICE_QUERY_SERVICE))
		.queryGoodsPrice(new GoodsPriceQuery(info.userId,info.token,goodsIds));
		if(price.status==1){
			StorageChange[] change=new StorageChange[ordered.length];
			for(int i=0,l=ordered.length;i<l;i++){
				PurchaseOrderGoods goods=
					new PurchaseOrderGoods(info.userId,orderId,ordered[i].goodsId,ordered[i].amount,new BigDecimal(price.goodsPrice[i].price));
				orderDAO.saveOrderedGoods(goods);
				change[i]=new StorageChange(info.userId,info.token,ordered[i].goodsId,-ordered[i].amount);
			}
			IceProxy.init(GoodsStorageManageHandlerPrxHelper.class, System.getProperty(OrderConstant.GOODS_STORAGE_MANAGE_SERVICE))
			.changeStorageBatch(change);//到此下单流程就应该 结束了，至于库存够不够应该是库存模块处理。
		}
		return null;
	}

	public OrderResult makeOrderFromCart(CartOrderInfo info, Current __current) {
		// TODO Auto-generated method stub
		/*1. query cart for ordered goods in cart
		 *2. invoke makeOrder(OrderInfo) and return
		 * */
		return null;
	}

}
