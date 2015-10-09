[["java:package:io.mycat.ep.v1"]]
module order {
	struct OrderedGoods{
		long goodsId;
		int amount;
	};
	sequence<OrderedGoods> OrderedGoodsSeq;
	struct OrderInfo{
		long userId;
		string token;
		
		string receiverName;
		string receiverPhone;
		string receiverProvince;
		string receiverCity;
		string receiverDistrict;
		string receiverStreet;
		string receiverStreetNumber;
		string extraInfo;
		
		OrderedGoodsSeq orderedGoods;
	};
	struct CartOrderInfo{
		long userId;
		string token;
		
		string receiverName;
		string receiverPhone;
		string receiverProvince;
		string receiverCity;
		string receiverDistrict;
		string receiverStreet;
		string receiverStreetNumber;
		string extraInfo;
		
		long cartId;
	};
	struct OrderResult{
		int status;
		long orderId;
		string orderCode;
	};
	interface OrderHandler{
		OrderResult makeOrder(OrderInfo info);
		OrderResult makeOrderFromCart(CartOrderInfo info);
	};
};
