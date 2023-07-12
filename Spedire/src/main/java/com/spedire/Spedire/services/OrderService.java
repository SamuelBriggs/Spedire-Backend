package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;

import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.models.Order;

public interface OrderService {

    ApiResponse acceptOrder(AcceptOrderRequest acceptOrderRequest) throws SpedireException;
    ApiResponse matchOrder (Request request);
}
