package com.spedire.Spedire.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.response.ApiResponse;

import com.spedire.Spedire.exceptions.SpedireException;

public interface OrderService {

    ApiResponse<?> acceptOrder(AcceptOrderRequest acceptOrderRequest) throws SpedireException;
    ApiResponse<?> matchOrder (DeliveryRequestDTO deliveryRequestDTO) throws JsonProcessingException;
}
