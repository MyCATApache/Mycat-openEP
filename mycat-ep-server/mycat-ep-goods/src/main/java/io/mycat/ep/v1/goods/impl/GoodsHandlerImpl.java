package io.mycat.ep.v1.goods.impl;

import Ice.Current;
import io.mycat.ep.v1.goods.GoodsPriceQuery;
import io.mycat.ep.v1.goods.GoodsPriceQueryResult;
import io.mycat.ep.v1.goods._GoodsHandlerDisp;
import org.springframework.stereotype.Service;

/**
 * Created by Liwh on 2015/11/4.
 */
@Service
public class GoodsHandlerImpl extends _GoodsHandlerDisp {

    @Override
    public GoodsPriceQueryResult queryGoodsPrice(GoodsPriceQuery query, Current __current) {
        //TODO
        return null;
    }
}
