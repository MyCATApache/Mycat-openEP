[["java:package:io.mycat.ep.v1"]]
module receiver {
	struct ReceiverQuery{
		long userId;
		string token;
	};
	struct PurchaseReceiver{
		long id;
		long userId;
		string name;
		string phone;
		string province;
		string city;
		string district;
		string street;
		string streetNumber;
		string extraInfo;
		bool defaultReceiver;
	};
	sequence<PurchaseReceiver> ReceiverSeq;
	struct ReceiverResult{
		int status;
		ReceiverSeq receivers;
	};
	interface PurchaseReceiverHandler{
		ReceiverResult queryPurchaseReceivers(ReceiverQuery query); 
	};
};
