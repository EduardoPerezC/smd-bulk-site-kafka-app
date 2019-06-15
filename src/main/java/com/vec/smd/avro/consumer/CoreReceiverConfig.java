package com.vec.smd.avro.consumer;

import com.vec.smd.avro.schema.BulkRequest;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class CoreReceiverConfig {


    @Value("${smd.bulk.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${smd.bulk.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${smd.bulk.kafka.appplicationId}")
    private String applicationId;


    //default implementation to accomplish Kafka Auto Configuration, see :
    //https://stackoverflow.com/questions/43142295/problems-adding-multiple-kafkalistenercontainerfactories/43142573

    @Bean
    public ConsumerFactory<Object, Object> kafkaConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(bulkCoreSenderConfig());

    }


    @Bean
    public Map<String, Object> bulkCoreSenderConfig(){

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put("schema.registry.url", schemaRegistryUrl);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,applicationId);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        return props;

    }


}
