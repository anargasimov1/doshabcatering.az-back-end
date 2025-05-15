package az.doshabcatering.doshabcatering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DoshabcateringApplication {

	public static void main(String[] args) {

		SpringApplication.run(DoshabcateringApplication.class, args);
	}

}
