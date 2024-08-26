package com.avrobasic;

import com.avroClasses.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class AvroProjectDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(AvroProjectDemo1Application.class, args);
//		Greeting greeting = Greeting.newBuilder()
//				.setGreeting("hii sumit")
//				.build();
		Greeting greeting1 = new Greeting();
		greeting1.setGreeting("hii sumit");

        Properties properties  = new Properties();
//        properties.setProperty("value.serializer",);
	}

}
