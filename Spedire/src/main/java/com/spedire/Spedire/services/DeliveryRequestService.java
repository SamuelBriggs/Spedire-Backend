package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.DeliveryRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Request;

public interface DeliveryRequestService {

    public void save(DeliveryRequest deliveryRequest);

    ApiResponse<?> findById(String id);

    ApiResponse<?> getallRequests();
}
