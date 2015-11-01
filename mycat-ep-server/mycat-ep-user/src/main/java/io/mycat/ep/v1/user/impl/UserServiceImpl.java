package io.mycat.ep.v1.user.impl;

import Ice.*;
import io.mycat.ep.ice.server.AbstractIceBoxService;
import io.mycat.ep.v1.user.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ILEX on 2015/11/1.
 */
@Service
public class UserServiceImpl  extends AbstractIceBoxService {

    @Autowired
    private UserHandler userHandler;

    @Override
    protected void addMyIceServiceObjFacets(ObjectAdapter adapter, Identity id) {
        //Add other Impl to Facets
    }

    @Override
    public Ice.Object createMyIceServiceObj(String[] args) {
        return userHandler;
    }
}
