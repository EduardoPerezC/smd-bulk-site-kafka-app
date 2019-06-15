package com.vec.smd.avro.controller;

import com.vec.smd.avro.producer.BulkRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vec.smd.avro.model.dto.BulkUploadRequest;

@RestController
@RequestMapping("genesys/sites")
public class BulkRequestController {

    @Autowired
    private BulkRequestSender bulkRequestSender;

    @PostMapping("/bulk")
    public void sendBulkRequest(@RequestBody BulkUploadRequest bulkRequest){

        bulkRequestSender.send(BulkUploadRequest.fromBean(bulkRequest));

    }






}
