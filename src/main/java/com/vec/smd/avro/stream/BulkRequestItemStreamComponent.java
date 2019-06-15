package com.vec.smd.avro.stream;

import com.vec.smd.avro.schema.BulkRequest;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class BulkRequestItemStreamComponent {


    @Bean
    public KStream<String, BulkRequest> bulkRequestKStream(StreamsBuilder bulkStreamBuilder){

        //this is a new change

        KStream<String, BulkRequest> kstream = bulkStreamBuilder.stream(Schemas.Topics.BULKREQUESTS.name(),Consumed.with(Schemas.Topics.BULKREQUESTS.keySerde(), Schemas.Topics.BULKREQUESTS.valueSerde()));

        kstream.foreach(new ForeachAction<String, BulkRequest>() {
            public void apply(String key, BulkRequest value) {
                System.out.println(key + ": " + value);
            }
        });

        return  kstream;

    }




}
