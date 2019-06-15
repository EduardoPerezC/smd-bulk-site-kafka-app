package com.vec.smd.avro.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class BulkRequestClient {

    private RestTemplate template;

    public BulkRequestClient(RestTemplateBuilder builder){
        template = builder.build();
    }


    public void sendBulkRequest(){

        String url = "http://localhost:8080/bulk/save";
        String requestJson = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);


    }

}
