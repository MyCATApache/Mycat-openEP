package io.mycat.ep.order.model;

import io.mycat.ep.v1.order.OrderInfo;
import me.jor.util.Help;

public class PurchaseOrder {
	private long id;
	private String receiverName;
	private String address;
	private String receiverPhone;
	private String orderCode;
	private int orderStatus;
	public PurchaseOrder() {
		super();
	}
	public PurchaseOrder(long id, String receiverName, String address, String receiverPhone, String orderCode,
			int orderStatus) {
		super();
		this.id = id;
		this.receiverName = receiverName;
		this.address = address;
		this.receiverPhone = receiverPhone;
		this.orderCode = orderCode;
		this.orderStatus = orderStatus;
	}
	public PurchaseOrder(OrderInfo info) {
		receiverName=info.receiverName;
		address=Help.convert(info.receiverProvince,"")+
				Help.convert(info.receiverCity,"")+
				Help.convert(info.receiverDistrict,"")+
				Help.convert(info.receiverStreet,"")+
				Help.convert(info.receiverStreetNumber,"")+
				Help.convert(info.extraInfo,"");
		receiverPhone=info.receiverPhone;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}
