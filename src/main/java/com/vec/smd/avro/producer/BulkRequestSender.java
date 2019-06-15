package com.vec.smd.avro.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.vec.smd.avro.schema.BulkRequest;


@Service
public class BulkRequestSender {

    @Autowired
    private  KafkaTemplate<String, BulkRequest> bulkRequestTemplate;

    @Value("${smd.bulk.kafka.request-topic}")
    private String bulkRequestTopic;

    public void send(BulkRequest bulkRequest){

        bulkRequestTemplate.send(bulkRequestTopic,bulkRequest);
    }

}
