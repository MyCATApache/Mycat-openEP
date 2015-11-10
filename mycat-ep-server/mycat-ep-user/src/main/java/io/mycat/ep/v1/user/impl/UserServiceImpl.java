package io.mycat.ep.v1.user.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.ice.server.Sl4jIceBoxServer;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.user.UserHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class UserServiceImpl  extends AbstractIceBoxService {

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {
        //Add other Impl to Facets
    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return  SpringUtil.getBean(UserHandlerImpl.class);
    }
}
