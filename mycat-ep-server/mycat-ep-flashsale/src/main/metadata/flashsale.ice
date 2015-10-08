[["java:package:io.mycat.ep.v1"]]
module flashsale {
	struct FlashSaleInfo{
		long userId;
		string token;
		
		long goodsId;
	};
	struct FlashSaleResult{
		int status;
		long flashSaleId;
		string pass;
		long goodsId;
		int amount=1;
	};
	interface FlashBuyHandler{
		FlashSaleResult flashBuy(FlashSaleInfo sale); 
		
	};
};
