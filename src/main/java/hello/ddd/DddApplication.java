package hello.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAsync 1번 방법
public class DddApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddApplication.class, args);
	}

}
