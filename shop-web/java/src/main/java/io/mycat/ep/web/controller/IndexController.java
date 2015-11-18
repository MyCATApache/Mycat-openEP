package io.mycat.ep.web.controller;

import io.mycat.ep.ice.utils.ICEClientUtil;
import io.mycat.ep.v1.user.ClientInfo;
import io.mycat.ep.v1.user.UserHandlerPrx;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user.client.UserHandlerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Liwh on 2015/11/4.
 */
@Controller
public class IndexController {


    @RequestMapping("*")
    public String index(){

        return "index";
    }

    @RequestMapping("/adduser")
    public String user(Model model){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.realname="12Name";
        clientInfo.signature="signature";
        clientInfo.password="123";
        clientInfo.phone="13800138"+(int)(Math.random()*1000);

        //UserHandlerPrx userHandlerPrx = UserHandlerClient.getServiceProxy();
        UserHandlerPrx userHandlerPrx = (UserHandlerPrx) ICEClientUtil.getSerivcePrx(UserHandlerPrx.class);

        UserSession session = userHandlerPrx.regist(clientInfo) ;


        String token = session.token;
        String userid =String.valueOf(session.userId) ;
        String status = String.valueOf(session.status);

        model.addAttribute("token",token);
        model.addAttribute("userid",userid);
        model.addAttribute("status",status);

        return "user";
    }



}
