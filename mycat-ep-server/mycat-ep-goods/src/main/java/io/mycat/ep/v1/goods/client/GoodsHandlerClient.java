package io.mycat.ep.v1.goods.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.goods.GoodsHandlerPrx;

/**
 * Created by Liwh on 2015/11/4.
 */
public class GoodsHandlerClient {

    public static GoodsHandlerPrx getServiceProxy(){
        return (GoodsHandlerPrx) ICEClientUtil.getSerivcePrx(GoodsHandlerPrx.class,null);
    }

}
