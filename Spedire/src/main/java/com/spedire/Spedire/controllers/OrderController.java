package com.spedire.Spedire.controllers;


import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequest;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/matchOrder")

    public ResponseEntity<?> matchOrder(@RequestBody DeliveryRequest deliveryRequest) throws SpedireException {
        var response = orderService.matchOrder(deliveryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/acceptOrder")
        public ResponseEntity<?> acceptOrder(@RequestBody AcceptOrderRequest acceptOrderRequest) throws SpedireException {
        var response = orderService.acceptOrder(acceptOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
