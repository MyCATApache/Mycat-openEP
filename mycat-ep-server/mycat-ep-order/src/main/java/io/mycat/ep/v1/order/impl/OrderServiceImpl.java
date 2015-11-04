package io.mycat.ep.v1.order.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.order.OrderHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class OrderServiceImpl extends AbstractIceBoxService {


    private OrderHandler orderHandler = SpringUtil.getBean(OrderHandlerImpl.class);

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return orderHandler;
    }
}
