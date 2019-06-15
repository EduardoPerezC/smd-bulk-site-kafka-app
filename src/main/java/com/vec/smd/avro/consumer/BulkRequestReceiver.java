package com.vec.smd.avro.consumer;

import com.vec.smd.avro.schema.BulkRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class BulkRequestReceiver {

    //@KafkaListener(topics = "${smd.bulk.kafka.request-topic}", clientIdPrefix = "bulkRequestClientId",
        //   containerFactory = "bulkRequestListenerContainerFactory")
    public void listen(BulkRequest bulkRequest){

            System.out.println(bulkRequest.toString());


    }
}
