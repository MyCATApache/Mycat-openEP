package io.mycat.ep.v1.flashsale.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.flashsale.FlashBuyHandlerPrx;

/**
 * Created by Liwh on 2015/11/3.
 */
public class FlashSaleHandlerClient {

    public static FlashBuyHandlerPrx getServiceProxy(){
        return (FlashBuyHandlerPrx) ICEClientUtil.getSerivcePrx(FlashBuyHandlerPrx.class,null);
    }
}
