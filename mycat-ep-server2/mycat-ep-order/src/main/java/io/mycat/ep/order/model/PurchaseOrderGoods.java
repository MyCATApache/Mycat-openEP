package io.mycat.ep.order.model;

import java.math.BigDecimal;

public class PurchaseOrderGoods {
	private long id;
	private long userId;
	private long orderId;
	private long goodsId;
	private int amount;
	private BigDecimal unitPrice;
	public PurchaseOrderGoods() {
		super();
	}
	public PurchaseOrderGoods(long id, long userId, long orderId, long goodsId, int amount, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.unitPrice = unitPrice;
	}
	public PurchaseOrderGoods(long userId, long orderId, long goodsId, int amount, BigDecimal unitPrice) {
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.unitPrice = unitPrice;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
