package com.vec.smd.avro.camel.processors;

import com.vec.smd.avro.utils.BulkUploadExcelFileUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;

public class BulkUploadInputFileProcessor implements Processor{

    private static Logger LOGGER = LoggerFactory.getLogger(BulkUploadInputFileProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        InputStream is = exchange.getIn().getBody(InputStream.class);
        //  BulkUploadExcelFileUtil.readFile(is);

    }

}



