package com.vec.smd.avro.stream;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.vec.smd.avro.schema.BulkRequest;
import com.vec.smd.avro.schema.BulkSiteItem;

import org.springframework.kafka.support.serializer.JsonSerde;


public class Schemas {

    public static class Topic<K, V> {

        private final String name;
        private final Serde<K> keySerde;
        private final Serde<V> valueSerde;

        Topic(final String name, final Serde<K> keySerde, final Serde<V> valueSerde) {
            this.name = name;
            this.keySerde = keySerde;
            this.valueSerde = valueSerde;
            Topics.ALL.put(name, this);
        }

        public Serde<K> keySerde() {
            return keySerde;
        }

        public Serde<V> valueSerde() {

            String schemaRegistryUrl = "http://localhost:8081";

            this.valueSerde.configure(Collections.singletonMap(
                    AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
                    schemaRegistryUrl),false);

            return valueSerde;
        }

        public String name() {
            return name;
        }

        public String toString() {
            return name;
        }
    }

    public static class Topics {

        public static final Map<String, Topic<?, ?>> ALL = new HashMap<>();
        public static Topic<String, BulkRequest> BULKREQUESTS;
        public static Topic<String, BulkSiteItem> BULKTESTSERVICE;


        static {
            createTopics();
        }

        private static void createTopics() {
            BULKREQUESTS = new Topic<>("SMD-BULKREQUEST-TEST-3", Serdes.String(), new SpecificAvroSerde<>());
            BULKTESTSERVICE = new Topic<>("SMD-BULKREQUEST-TEST-3", Serdes.String(),new SpecificAvroSerde<>());
            //ORDER_VALUE_SERDE = new SpecificAvroSerde<>();
        }
    }




}
