package ns.feigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudFeignClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignClientDemoApplication.class, args);
	}

}
