package com.vec.smd.avro.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.vec.smd.avro.schema.*;
import org.apache.commons.lang3.StringUtils;


public class BulkUploadExcelFileUtil {

    public static BulkRequest readFile(InputStream is )throws InvalidFormatException,IOException {

        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        BulkRequest.Builder requestBuilder = BulkRequest.newBuilder().setRequestID(1)
                .setUserEmail("user@mail.com")
                .setStatus("CREATED");

        List<BulkSiteItem> siteItems = new ArrayList<>();
        sheet.forEach(row -> {

            if (row.getRowNum() >= 2){
                BulkSiteItem.Builder builder = BulkSiteItem.newBuilder();
                row.forEach(cell -> {

                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (StringUtils.isNotEmpty(cellValue)){

                        switch (cell.getColumnIndex()){
                            case 1:
                                builder.setVzSiteId(cellValue);
                                break;
                            case 2:
                                builder.setCustomerId(cellValue);
                                break;
                            case 3:
                                builder.setCustomerName(cellValue);
                                break;
                            case 4:
                                builder.setCountry(cellValue);
                                break;
                            case 5:
                                builder.setState(cellValue);
                                break;
                            case 6:
                                builder.setCity(cellValue);
                                break;
                            case 7:
                                builder.setZipCode(cellValue);
                                break;
                            case 8:
                                builder.setAddressLine1(cellValue);
                                break;
                            case 9:
                                builder.setAddressLine2(cellValue);
                                break;
                            case 10:
                                builder.setStatus(cellValue);
                                break;
                        }
                    }
                });
                siteItems.add(builder.build());
            }
        });

        requestBuilder.setBulkItems(siteItems);

        workbook.close();

        return requestBuilder.build();
    }


}
