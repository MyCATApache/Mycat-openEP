package io.mycat.ep.v1.flashsale.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.flashsale.FlashBuyHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class FlashSaleServiceImpl extends AbstractIceBoxService {

    private FlashBuyHandler flashBuyHandler = SpringUtil.getBean(FlashSaleHandlerImpl.class);

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return flashBuyHandler;
    }
}
