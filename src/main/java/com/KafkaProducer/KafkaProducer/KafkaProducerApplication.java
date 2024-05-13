package com.KafkaProducer.KafkaProducer;

import com.KafkaProducer.KafkaProducer.dto.SModel;

import io.confluent.kafka.serializers.KafkaJsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Properties;


@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {


		SpringApplication.run(KafkaProducerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//http://localhost:29092/
		Properties config = new Properties();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"http://localhost:29092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());

		KafkaProducer<String, SModel> producer = new KafkaProducer<String, SModel>(config);


		SModel sModel = new SModel(212,"Baran","Sepet");

		ProducerRecord<String, SModel> record = new ProducerRecord<String, SModel>("serializer", sModel);
		producer.send(record);
		producer.flush();



		/*for (int i = 0; i < 6; i++) {
			int key = i % 6;
			JSONObject data = new JSONObject();
			data.put("key", key);
			data.put("value","deneme"+key);
			data.put("view","Login");
			kafkaTemplate.send("Jsons",key,String.valueOf(key),data.toString());
			kafkaTemplate.flush();
		}*/



	}
}


