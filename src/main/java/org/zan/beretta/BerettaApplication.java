package org.zan.beretta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class BerettaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BerettaApplication.class, args);
	}

}
