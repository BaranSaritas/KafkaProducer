package com.KafkaProducer.KafkaProducer;

import com.KafkaProducer.KafkaProducer.dto.SModel;

import io.confluent.kafka.serializers.KafkaJsonDeserializer;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


@SpringBootApplication
@EnableScheduling
@Slf4j
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {


		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers ;


	@KafkaListener(topics = "first-topic",containerFactory = "createListenerContainerFactory")
	public void consumeSells(String message){
		log.info("message {}",message);
	}

	@KafkaListener(topics = "model-topic",containerFactory ="createListenerContainerFactoryModel")
	public void consumeJson(SModel model){
		log.info("message {}",model.getName());
	}

	@Override
	public void run(String... args) throws Exception {

		Logger logger = LoggerFactory.getLogger(KafkaProducerApplication.class.getName());

		String topic ="first-topic";
		String bootstrapServers = "localhost:29092";
		String groupId = "my-fourth-application";

		// Create consumer config


		//create consumer
	//	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

		//subscribe consumer to our topic(s)
	//	consumer.subscribe(Arrays.asList(topic));

		//poll for new data
		//for demo purpose using true
	/*	while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			records.forEach(record->{
				logger.info("key: "+ record.key()+", value: "+ record.value());
				logger.info("partition: "+record.partition()+", offset: "+record.offset());
			});
		}
*/

	}
}



//http://localhost:29092/

// Producer senaryolari
		/*

		Properties config = new Properties();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"http://localhost:29092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());

		KafkaProducer<String, SModel> producer = new KafkaProducer<String, SModel>(config);


		SModel sModel = new SModel(212,"Baran","Sepet");

		ProducerRecord<String, SModel> record = new ProducerRecord<String, SModel>("serializer", sModel);
		producer.send(record);
		producer.flush();


*/
		/*for (int i = 0; i < 6; i++) {
			int key = i % 6;
			JSONObject data = new JSONObject();
			data.put("key", key);
			data.put("value","deneme"+key);
			data.put("view","Login");
			kafkaTemplate.send("Jsons",key,String.valueOf(key),data.toString());
			kafkaTemplate.flush();
		}*/

// Consumer senaryolari
/*
Properties config = new Properties();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_group_deneme");
		config.put(ConsumerConfig.CLIENT_ID_CONFIG, "kafka_client_deneme");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //earliest
		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
		consumer.subscribe(Arrays.asList("serializer"));

ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMinutes(1));

		for (ConsumerRecord<String, String> record : consumerRecords) {
		System.out.println(record.value());
		//commit atma olayi hizi azaltir ama bir islem bitmeden commit atmak mantikli degil ciddi islemlerde
		//	consumer.commitSync();   // zooekeeper da okundu bilgisini veriyorsun
		}

		consumer.commitSync();   // tek tek kontrole almadan 1 tane commit attin hata da cikabilir

		*/
