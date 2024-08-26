package com.producer.avro.config;

import com.producer.avro.classes.Student;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfigProducer {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partition}")
    private int partition;

    @Value("${topic.replicationFactor}")
    private short rf;

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String kafkaPort;

//    @Value("${spring.kafka.producer.properties.schema.registry.url}")
//    private String schemaRegistryUrl;


    @Bean
    public HttpMessageConverter<GenericRecord> avroHttpMessageConverter() {
        return new AvroHttpMessageConverter();
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(topicName, partition,rf);
    }

//    @Bean
//    public KafkaTemplate<String, Student> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, Student> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaPort);
//        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName()); //This is for json value
//        return  props;
//    }

}
