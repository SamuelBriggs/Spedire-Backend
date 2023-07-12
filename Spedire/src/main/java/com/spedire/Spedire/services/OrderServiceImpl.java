package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.MatchedUserDto;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Order;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.OrderRepository;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    @Override
    public ApiResponse acceptOrder(AcceptOrderRequest acceptOrderRequest) throws SpedireException {
        Optional<Order> foundOrder = orderRepository.findById(acceptOrderRequest.getOrderId());
        Order order = foundOrder.get();
        order.setAccepted(true);
        String senderId = order.getSenderId();
        Optional<User> foundSender = userRepository.findById(senderId);
        User sender = foundSender.get();
        Optional<User> foundCarrier = userRepository.findById(acceptOrderRequest.getCarrierId());
        User carrier = foundCarrier.get();
        sender.setMatchedUserDTO(MatchedUserDto.builder().carrierName(carrier.getFirstName())
                .carrierPhoneNumber(carrier.getPhoneNumber()).build());
        userRepository.save(sender);

        return ApiResponse.builder().success(true).message("Great, we will notify the sender right away!").data(sender.getPhoneNumber()).build();
    }

    @Override
    public ApiResponse matchOrder(Request requestItem) {
      List<Order> unPairedOrders = orderRepository.findAll();
        System.out.println(unPairedOrders + " unpaired orders");
        List<Order> orderList = new ArrayList<>();
      for(Order order: unPairedOrders){
          if (order.getDestination().getReceiverLocation().getCity().
                  equals(requestItem.getDestination().getCity())  && order.getPickUp().getCurrentLocation().getCity().
                  equals(requestItem.getCurrentLocation().getCity())) orderList.add(order);
      }

        orderList.sort((o1, o2) -> {
            if (o1.getCreatedAt() == null || o2.getCreatedAt() == null)
                return 0;
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        });
      if(orderList.size() > 0){
          return ApiResponse.builder().message("Matching Orders Fetched Successfully").data(orderList).success(true).build();
      }

      return ApiResponse.builder().message("No Found Match Currently").success(false).build();
    }
}
