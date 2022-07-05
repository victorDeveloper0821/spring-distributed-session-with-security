package idv.victor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "idv.victor")
@EntityScan("idv.victor.entity")
@EnableJpaRepositories("idv.victor.repository")
public class SpringDistributedSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDistributedSessionApplication.class, args);
	}

}
