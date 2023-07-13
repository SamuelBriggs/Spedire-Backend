package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Order;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SpedireOrderService implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public ApiResponse matchOrder(Request requestItem) {
      List<Order> unPairedOrders = orderRepository.findAll();
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
      if(orderList.size() < 1){
          return ApiResponse.builder().message("Matching Orders Fetched Successfully").data(orderList).success(true).build();
      }

        return ApiResponse.builder().message("No Found Match Currently").success(false).build();
    }
}
