package io.mycat.ep.v1.goods.impl;

import Ice.*;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;

/**
 * Created by Liwh on 2015/11/4.
 */
public class GoodsHandlerServiceImpl extends AbstractIceBoxService {


    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return SpringUtil.getBean(GoodsHandlerImpl.class);
    }
}
