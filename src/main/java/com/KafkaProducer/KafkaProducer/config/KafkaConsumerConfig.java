package com.KafkaProducer.KafkaProducer.config;

import com.KafkaProducer.KafkaProducer.dto.SModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers ;
    String groupId = "my-fourth-application";
    @Bean
    public <T> ConsumerFactory<String,T> getConsumerFactory(Class<T> consumerClass ){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//earliest/latest/none
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(properties,new StringDeserializer(),new JsonDeserializer<>(consumerClass));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> createListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(getConsumerFactory(String.class));
        return listenerContainerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SModel> createListenerContainerFactoryModel(){
        ConcurrentKafkaListenerContainerFactory<String, SModel> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(getConsumerFactory(SModel.class));
        return listenerContainerFactory;
    }
}
