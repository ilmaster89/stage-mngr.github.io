package itsar.selectmngr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SelectMngrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectMngrApplication.class, args);
	}

}
