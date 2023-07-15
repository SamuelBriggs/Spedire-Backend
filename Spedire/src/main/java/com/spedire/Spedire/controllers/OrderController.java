package com.spedire.Spedire.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
@Slf4j

public class OrderController {
    private OrderService orderService;

    @PostMapping("/matchOrder")

    public ResponseEntity<?> matchOrder(@RequestBody DeliveryRequestDTO deliveryRequestDTO) throws SpedireException {
        log.info("lets see if its getting here");
        ApiResponse<?> response = null;
        try {
            response = orderService.matchOrder(deliveryRequestDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/acceptOrder")
        public ResponseEntity<?> acceptOrder(@RequestBody AcceptOrderRequest acceptOrderRequest)
            throws SpedireException {
        log.info("In the acceptOrder controller");
        var response = orderService.acceptOrder(acceptOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
