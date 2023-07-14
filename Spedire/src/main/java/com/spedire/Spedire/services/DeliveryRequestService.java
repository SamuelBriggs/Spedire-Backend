package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Request;

public interface DeliveryRequestService {

    public void saveDeliveryRequest(Request deliveryRequestDTO);

    ApiResponse<?> findById(String id);

    ApiResponse<?> getallRequests();
}
