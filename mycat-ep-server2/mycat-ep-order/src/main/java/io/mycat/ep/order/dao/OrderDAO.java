package io.mycat.ep.order.dao;

import io.mycat.ep.order.model.PurchaseOrder;
import io.mycat.ep.order.model.PurchaseOrderGoods;

public interface OrderDAO {

	void saveOrder(PurchaseOrder order);

	void saveOrderedGoods(PurchaseOrderGoods goods);

}
