package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Order;

public interface OrderService {

    ApiResponse acceptOrder(String orderId, String carrierPhoneNumber) throws SpedireException;
}
