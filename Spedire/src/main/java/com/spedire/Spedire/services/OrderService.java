package com.spedire.Spedire.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.request.SaveOrderRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;

import com.spedire.Spedire.dtos.response.OrderResponse;
import com.spedire.Spedire.exceptions.InvalidOrderException;
import com.spedire.Spedire.exceptions.SpedireException;

import java.text.ParseException;

public interface OrderService {

    ApiResponse<?> acceptOrder(AcceptOrderRequest acceptOrderRequest) throws SpedireException;
    ApiResponse<?> matchOrder (DeliveryRequestDTO deliveryRequestDTO) throws JsonProcessingException;
    ApiResponse<?> saveOrder(SaveOrderRequest saveOrderRequest, String senderId) throws SpedireException, ParseException;
    OrderResponse findOrderById(String id) throws InvalidOrderException;
}
