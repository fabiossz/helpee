package br.org.iscmsp.helpee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@EnableSpringDataWebSupport
public class HelpeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpeeApplication.class, args);
	}

}
