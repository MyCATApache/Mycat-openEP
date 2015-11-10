package io.mycat.ep.v1.user.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.user.session.UserSessionHandlerPrx;

/**
 * Created by Liwh on 2015/11/4.
 */
public class UserSessionHandlerClient {

    public static UserSessionHandlerPrx getServiceProxy(){
        return (UserSessionHandlerPrx) ICEClientUtil.getSerivcePrx(UserSessionHandlerClient.class,null);
    }

}
