package mycatep.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@Configuration    	//配置控制  
@ComponentScan    //组件扫描  
@EnableAutoConfiguration //启用自动配置  
*/

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		//启动Spring Boot项目的唯一入口  
/*		SpringApplication app = new SpringApplication(Startup.class);  
        app.setWebEnvironment(true);  
        app.setShowBanner(false);  
          
        Set<Object> set = new HashSet<Object>();  
        set.add("classpath:applicationContext.xml");  
        app.setSources(set);  
        app.run(args);  */
		SpringApplication.run(MainApplication.class, args);

	}

}
