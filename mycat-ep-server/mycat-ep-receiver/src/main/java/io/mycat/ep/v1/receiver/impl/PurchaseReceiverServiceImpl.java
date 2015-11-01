package io.mycat.ep.v1.receiver.impl;

import Ice.*;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.v1.receiver.PurchaseReceiverHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ILEX on 2015/11/1.
 */
@Service
public class PurchaseReceiverServiceImpl extends AbstractIceBoxService {

    @Autowired
    private PurchaseReceiverHandler purchaseReceiverHandler;

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return purchaseReceiverHandler;
    }
}
