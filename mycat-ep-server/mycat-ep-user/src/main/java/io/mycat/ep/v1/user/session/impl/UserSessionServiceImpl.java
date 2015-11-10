package io.mycat.ep.v1.user.session.impl;

import Ice.Identity;
import Ice.ObjectAdapter;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.util.SpringUtil;
import io.mycat.ep.v1.user.session.UserSessionHandler;

/**
 * Created by ILEX on 2015/11/1.
 */

public class UserSessionServiceImpl extends AbstractIceBoxService {


    private UserSessionHandler userSessionHandler =  SpringUtil.getBean(UserSessionHandlerImpl.class);


    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {

    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return userSessionHandler;
    }
}
