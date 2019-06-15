package com.vec.smd.avro.stream;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.FailOnInvalidTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.CleanupConfig;


import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class CoreKafkaStreamConfig {

    @Value("${smd.bulk.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${smd.bulk.kafka.appplicationId}")
    private String applicationId;

    @Value("${smd.bulk.schema.registry.url}")
    private String schemaRegistryUrl;


    public void setDefaults(Map<String, Object> config) {
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, FailOnInvalidTimestamp.class);

    }


    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "default");
        setDefaults(config);
        return new KafkaStreamsConfiguration(config);
    }


    @Bean(name = "bulkStreamBuilder")
    public StreamsBuilderFactoryBean bulkStreamBuilderFactoryBean(){

        Map<String, Object> config = new HashMap<>();
        setDefaults(config);
        //this is a workaround for issue, see : https://stackoverflow.com/questions/54295588/kafka-streams-failed-to-rebalance-error

        //config.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schemaRegistryUrl);


        KafkaStreamsConfiguration streamsConfiguration = new KafkaStreamsConfiguration(config);
        CleanupConfig cleanupConfig = new CleanupConfig(Boolean.TRUE, Boolean.TRUE);


        return new StreamsBuilderFactoryBean(streamsConfiguration, cleanupConfig);


    }


}
