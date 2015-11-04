package io.mycat.ep.v1.goods.stock.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.goods.stock.GoodsStorageManageHandlerPrx;

/**
 * Created by Liwh on 2015/11/3.
 */
public class GoodsStorageManageHandlerClient {

    public static GoodsStorageManageHandlerPrx getServiceProxy(){
        return (GoodsStorageManageHandlerPrx) ICEClientUtil.getSerivcePrx(GoodsStorageManageHandlerPrx.class,null);
    }
}
