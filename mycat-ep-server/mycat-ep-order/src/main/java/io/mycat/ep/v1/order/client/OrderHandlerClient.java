package io.mycat.ep.v1.order.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.order.OrderHandlerPrx;

/**
 * Created by Liwh on 2015/11/3.
 */
public class OrderHandlerClient {

    public static OrderHandlerPrx getServiceProxy(){
        return (OrderHandlerPrx) ICEClientUtil.getSerivcePrx(OrderHandlerPrx.class,null);
    }
}
