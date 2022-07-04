package idv.victor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "idv.victor")
public class SpringDistributedSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDistributedSessionApplication.class, args);
	}

}
