package com.vec.smd.avro.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import com.vec.smd.avro.schema.BulkRequest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

//@Configuration
public class BulkRequestReceiverConfig {

    @Autowired
    private CoreReceiverConfig coreReceiverConfig;

    @Bean
    public ConsumerFactory<String, BulkRequest> bulkRequestConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(
                coreReceiverConfig.bulkCoreSenderConfig()
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BulkRequest> bulkRequestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BulkRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bulkRequestConsumerFactory());

        return factory;
    }


}
