package com.vec.smd.avro.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.vec.smd.avro.schema.BulkRequest;


@Configuration
public class BulkRequestSenderConfig {


    @Autowired
    private CoreSenderConfig coreSenderConfig;


    @Bean
    public ProducerFactory<String, BulkRequest> bulkRequestProducerFactory() {
        return new DefaultKafkaProducerFactory<>(coreSenderConfig.bulkProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, BulkRequest> bulkRequestTemplate() {
        return new KafkaTemplate<String, BulkRequest>(bulkRequestProducerFactory());
    }

}
