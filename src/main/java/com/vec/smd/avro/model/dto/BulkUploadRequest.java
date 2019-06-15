package com.vec.smd.avro.model.dto;

import java.util.ArrayList;
import java.util.List;
import com.vec.smd.avro.schema.BulkRequest;
import com.vec.smd.avro.schema.BulkSiteItem;

public class BulkUploadRequest {

    private long requestID;
    private String userID;
    private String userEmail;
    private String status;
    private List<BulkUploadRequestItem> bulkItems;

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BulkUploadRequestItem> getBulkItems() {
        return bulkItems;
    }

    public void setBulkItems(List<BulkUploadRequestItem> bulkItems) {
        this.bulkItems = bulkItems;
    }

    public static  BulkRequest fromBean(BulkUploadRequest bulkUploadRequest){

        List<BulkSiteItem> items = new ArrayList<>();

        bulkUploadRequest.bulkItems.forEach(bsi ->{
            items.add(  BulkSiteItem.newBuilder().setItemId(bsi.getItemId())
                    .setStatus(bsi.getStatus())
                    .setAddressLine2(bsi.getAddressLine2())
                    .setAddressLine1(bsi.getAddressLine1())
                    .setZipCode(bsi.getZipCode())
                    .setCity(bsi.getCity())
                    .setCountry(bsi.getCountry())
                    .setCustomerId(bsi.getCustomerId())
                    .setCustomerName(bsi.getCustomerName())
                    .setVzSiteId(bsi.getVzSiteId())
                    .build());
        });

        return  BulkRequest.newBuilder().setRequestID(bulkUploadRequest.getRequestID())
                .setUserID(bulkUploadRequest.getUserID())
                .setUserEmail(bulkUploadRequest.getUserEmail())
                .setStatus(bulkUploadRequest.getStatus())
                .setBulkItems(items).build();

    }


}
