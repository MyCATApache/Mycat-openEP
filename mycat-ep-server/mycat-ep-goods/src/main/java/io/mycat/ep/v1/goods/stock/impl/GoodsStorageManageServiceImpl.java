package io.mycat.ep.v1.goods.stock.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.goods.stock.GoodsStorageManageHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class GoodsStorageManageServiceImpl extends AbstractIceBoxService {

    private GoodsStorageManageHandler storageManageHandler = SpringUtil.getBean(GoodsStorageManageHandlerImpl.class);

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return storageManageHandler;
    }
}
