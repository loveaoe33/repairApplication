package repair_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"repair_App"})
@EnableJpaRepositories(basePackages={"jpaConnection"})abstract
@EntityScan(basePackages= {"repairObject"})
public class RepairApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairApplication.class, args);
	}

}
