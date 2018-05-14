/**
 * @author Jordan
 *
 * Main class for running he spring boot application.
 * */
package org.axonframework.sample.axonmsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonMsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonMsgApplication.class, args);
	}

}
