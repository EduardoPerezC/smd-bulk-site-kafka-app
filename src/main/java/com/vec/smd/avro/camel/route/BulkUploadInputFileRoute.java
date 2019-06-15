package com.vec.smd.avro.camel.route;

import com.vec.smd.avro.camel.processors.BulkUploadInputFileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class BulkUploadInputFileRoute extends RouteBuilder {

    @Value("${smd.bulk-inputfileDir}")
    private String inputfileDir;

    public void configure() throws Exception {

        from("file://" + inputfileDir + "?delete=true&include=(?i).*.xlsx")
                .process(new BulkUploadInputFileProcessor())
                .end();

    }

}
