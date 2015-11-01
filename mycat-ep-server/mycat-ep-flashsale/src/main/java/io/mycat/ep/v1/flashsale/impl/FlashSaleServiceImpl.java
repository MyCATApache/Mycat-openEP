package io.mycat.ep.v1.flashsale.impl;

import Ice.*;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.v1.flashsale.FlashBuyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ILEX on 2015/11/1.
 */
@Service
public class FlashSaleServiceImpl extends AbstractIceBoxService {

    @Autowired
    private FlashBuyHandler flashBuyHandler;

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return flashBuyHandler;
    }
}
