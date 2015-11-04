package io.mycat.ep.v1.receiver.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.receiver.PurchaseReceiverHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class PurchaseReceiverServiceImpl extends AbstractIceBoxService {

    private PurchaseReceiverHandler purchaseReceiverHandler = SpringUtil.getBean(PurchaseReceiverHandlerImpl.class);

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return purchaseReceiverHandler;
    }
}
