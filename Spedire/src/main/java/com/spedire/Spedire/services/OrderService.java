package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Request;

public interface OrderService {
    ApiResponse matchOrder (Request request);
}
