package io.mycat.ep.v1.receiver.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.receiver.PurchaseReceiverHandlerPrx;

/**
 * Created by Liwh on 2015/11/3.
 */
public class PurchaseReceiverHandlerClient {
    public static PurchaseReceiverHandlerPrx getServiceProxy(){
        return (PurchaseReceiverHandlerPrx) ICEClientUtil.getSerivcePrx(PurchaseReceiverHandlerPrx.class,null);
    }
}
