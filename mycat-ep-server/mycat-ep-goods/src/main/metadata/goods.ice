[["java:package:io.mycat.ep.v1"]]
module goods {
	sequence<long> GoodsIdSeq;
	struct GoodsPriceQuery{
		long userId;
		string token;
		GoodsIdSeq goodsIds;
	};
	struct GoodsPrice{
		long goodsId;
		string price;
	};
	sequence<GoodsPrice> GoodsPriceSeq;
	struct GoodsPriceQueryResult{
		int status;
		GoodsPriceSeq goodsPrice;
	};
	interface GoodsHandler{
		GoodsPriceQueryResult queryGoodsPrice(GoodsPriceQuery query);
	};
	module stock{
		struct StorageChange{
			long userId;
			string token;
			
			long goodsId;
			int amount;
		};
		struct StorageChangeResult{
			int status;
		};
		struct StorageQuery{
			long userId;
			string token;
			long goodsId;
		};
		struct StorageAmount{
			int status;
			int amount;
		};
		sequence<StorageChange> StorageChangeSeq;
		interface GoodsStorageManageHandler{
			StorageAmount queryGoodsAmount(StorageQuery query);
			StorageChangeResult changeStorage(StorageChange change);
			StorageChangeResult changeStorageBatch(StorageChangeSeq change);
		};
	};
};
