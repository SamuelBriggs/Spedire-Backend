package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.MatchedUserDto;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Order;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class SpedireOrderService implements OrderService {

    private OrderRepository orderRepository;

    private UserService userService;


    @Override
    public ApiResponse acceptOrder(String orderId, String carrierPhoneNumber) throws SpedireException {
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        Order order = foundOrder.get();
        order.setAccepted(true);
        String senderId = order.getSenderId();
        User sender = userService.findByPhoneNumber(senderId);
        User carrier = userService.findByPhoneNumber(carrierPhoneNumber);
        sender.setMatchedUserDTO(MatchedUserDto.builder().carrierName(carrier.getFirstName())
                .carrierPhoneNumber(carrier.getPhoneNumber()).build());

        return ApiResponse.builder().success(true).message("Great, we will notify the sender right away!").data(sender.getPhoneNumber()).build();
    }
}
