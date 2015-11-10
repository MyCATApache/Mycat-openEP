package io.mycat.ep.v1.user.client;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.user.UserHandlerPrx;

/**
 * Created by Liwh on 2015/11/3.
 */
public class UserHandlerClient {

    public static UserHandlerPrx  getServiceProxy(){

       return (UserHandlerPrx) ICEClientUtil.getSerivcePrx(UserHandlerPrx.class,null);

    }
}
