package mycatep.admin.ctrl;

import io.mycat.ep.v1.user.ClientInfo;
import io.mycat.ep.v1.user.UserHandlerPrx;
import io.mycat.ep.v1.user.UserSession;
import io.mycat.ep.v1.user.client.UserHandlerClient;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/test")
	public String test(Model model) {

		ClientInfo clientInfo = new ClientInfo();
	    clientInfo.realname="12Name";
	    clientInfo.signature="signature";
	    clientInfo.password="123";
	    clientInfo.phone="13800138"+(int)(Math.random()*1000);
	
	    UserHandlerPrx userHandlerPrx = UserHandlerClient.getServiceProxy();
	
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