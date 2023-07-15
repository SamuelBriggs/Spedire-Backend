package com.spedire.Spedire.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.MatchedUserDto;
import com.spedire.Spedire.dtos.response.OrderListDtoResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.Address;
import com.spedire.Spedire.models.Order;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.OrderRepository;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class SpedireOrderService implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;
    private final DeliveryRequestService deliveryRequestService;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ApiResponse<?> acceptOrder(AcceptOrderRequest acceptOrderRequest) throws SpedireException {
        log.info("In the acceptOrder service");
        Optional<Order> foundOrder = orderRepository.findById(acceptOrderRequest.getOrderId());
        Order order = foundOrder.get();
        order.setAccepted(true);
        String senderId = foundOrder.get().getSenderId();
        String carrierId = foundOrder.get().getCarrierId();
        log.info("senderId: " + senderId);
        log.info("carrierId: " + carrierId);
        Optional<User> foundSender = userRepository.findById(senderId);
        User sender = foundSender.get();
        Optional<User> foundCarrier = userRepository.findById(carrierId);
        User carrier = foundCarrier.get();
        sender.setMatchedUserDTO(MatchedUserDto.builder().carrierName(carrier.getFirstName())
                .carrierPhoneNumber(carrier.getPhoneNumber()).build());
        userRepository.save(sender);

        return ApiResponse.builder().success(true).message("Great, we will notify the sender right away!").data(sender.getPhoneNumber()).build();
    }

    @Override
    public ApiResponse<?> matchOrder(DeliveryRequestDTO deliveryRequestDTO) throws JsonProcessingException {

        Request deliveryRequest = buildDeliveryRequest(deliveryRequestDTO);

        String currentCity = deliveryRequest.getCurrentLocation().getCity();
        String destinationCity = deliveryRequest.getDestination().getCity();

        JsonNode jsonNode = objectMapper.readTree(currentCity);
        JsonNode jsonNode1 = objectMapper.readTree(destinationCity);

        String newCurrentCity = jsonNode.get("label").asText();
        String newDestinationCity = jsonNode1.get("label").asText();

        List<Order> unPairedOrders = orderRepository.findAll();

        List<Order> orderList = new ArrayList<>();
      for(Order order: unPairedOrders){
          if (order.getDestination().getReceiverLocation().getCity().
                  equals(newDestinationCity)  && order.getPickUp().getCurrentLocation().getCity().
                  equals(newCurrentCity)) orderList.add(order);
      }

        orderList.sort((o1, o2) -> {
            if (o1.getCreatedAt() == null || o2.getCreatedAt() == null)
                return 0;
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        });


      var response =  orderList.stream().map(this::convertFromOrderToOrderListDto).toList();

      if(orderList.size() > 0){
          return ApiResponse.builder().message("Matching Orders Fetched Successfully").data(response).success(true).build();
      }

      return ApiResponse.builder().message("No Found Match Currently").success(false).data(response).build();
    }

    private static Request buildDeliveryRequest(DeliveryRequestDTO deliveryRequestDTO) {
        return Request.builder().
                currentLocation(Address.builder().
                        city(deliveryRequestDTO.getCurrentLocationCity()).
                        landMark(deliveryRequestDTO.getCurrentLocationLandmark()).
                        streetName(deliveryRequestDTO.getCurrentLocationStreetName()).
                        streetNumber(deliveryRequestDTO.getCurrentLocationHouseNumber()).build()).
                destination(Address.builder().
                        city(deliveryRequestDTO.getDestinationCity()).
                        streetNumber(deliveryRequestDTO.getDestinationHouseNumber()).
                        landMark(deliveryRequestDTO.getDestinationLandmark()).
                        streetName(deliveryRequestDTO.getDestinationStreetName()).build()).build();
    }
    private OrderListDtoResponse convertFromOrderToOrderListDto(Order order){
      return   OrderListDtoResponse.builder().senderName(order.getPickUp().getSenderName()).orderId(order.getId()).senderName(
                order.getPickUp().getSenderName()).senderId(order.getSenderId()).
                senderPhoneNumber(order.getPickUp().getPhoneNumber()).
                currentLocationStreetName(order.getPickUp().getCurrentLocation().getStreetName()).
                destinationStreetName(order.getDestination().getReceiverLocation().getStreetName()).destinationLandmark(order.getDestination().getReceiverLocation().getLandMark()).build();

    }
}
