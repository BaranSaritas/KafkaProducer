package com.KafkaProducer.KafkaProducer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {


		SpringApplication.run(KafkaProducerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 61; i++) {
			int key = i % 6;
			kafkaTemplate.send("java_test",key,String.valueOf(key),"deneme "+i);
			kafkaTemplate.flush();
		}
	}
}


