package com.spedire.Spedire.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.dtos.request.AcceptOrderRequest;
import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.request.SaveOrderRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.MatchedUserDto;
import com.spedire.Spedire.dtos.response.OrderListDtoResponse;
import com.spedire.Spedire.dtos.response.OrderResponse;
import com.spedire.Spedire.exceptions.InvalidOrderException;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.*;
import com.spedire.Spedire.repositories.OrderRepository;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.spedire.Spedire.utils.AppUtils.INVALID_ORDER;
import static com.spedire.Spedire.utils.AppUtils.ORDER_PROCESSED;

@AllArgsConstructor
@Service
@Slf4j
public class SpedireOrderService implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;
    private final DeliveryRequestService deliveryRequestService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
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
          if (order.getReceiverDestination().getReceiverDestination().getCity().
                  equals(newDestinationCity)  && order.getPickUpLocation().getCurrentLocation().getCity().
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

    @Override
    public ApiResponse<?> saveOrder(SaveOrderRequest saveOrderRequest, String senderId) throws ParseException {
        Order order = buildOrderRequest(saveOrderRequest, senderId);
        orderRepository.save(order);
        return ApiResponse.builder().message(ORDER_PROCESSED).success(true).data("").build();
    }

    private Order buildOrderRequest(SaveOrderRequest saveOrderRequest, String senderId) throws ParseException {
        Order order = new Order();
        BigDecimal cost = new BigDecimal(saveOrderRequest.getCostOfItem());
        Date date = dateConverter(saveOrderRequest);
        LocalTime time = timeConverter(saveOrderRequest);
        Address senderAddress = getSenderAddress(saveOrderRequest);
        Address receiverAddress = getReceiverAddress(saveOrderRequest);
        PickUp pickUp = modelMapper.map(saveOrderRequest, PickUp.class);
        Reciever reciever = modelMapper.map(saveOrderRequest, Reciever.class);
        order.setPickUpLocation(pickUp);
        order.getPickUpLocation().setCurrentLocation(senderAddress);
        order.setReceiverDestination(reciever);
        order.getReceiverDestination().setReceiverDestination(receiverAddress);
        order.setCostOfDelivery(cost);
        order.setDueDate(date);
        order.setDueTime(time);
        order.setReceiverName(saveOrderRequest.getReceiverName());
        order.setDescription(saveOrderRequest.getDescription());
        order.setType(ItemType.valueOf(saveOrderRequest.getItemType()));
        order.setCreatedAt(LocalDateTime.now());
        order.setSenderId(senderId);
        return order;
    }

    private Address getReceiverAddress(SaveOrderRequest saveOrderRequest) {
        return Address.builder()
                .landMark(saveOrderRequest.getReceiverLandmark())
                .streetName(saveOrderRequest.getReceiverStreetName())
                .city(saveOrderRequest.getReceiverDestination())
                .streetNumber(saveOrderRequest.getReceiverHouseNumber())
                .build();
    }

    private static Address getSenderAddress(SaveOrderRequest saveOrderRequest) {
        return Address.builder()
                .city(saveOrderRequest.getPickUpCity())
                .streetName(saveOrderRequest.getPickUpStreetName())
                .landMark(saveOrderRequest.getPickUpLandmark())
                .streetNumber(saveOrderRequest.getPickHouseNumber())
                .build();
    }

    @Override
    public OrderResponse findOrderById(String id) throws InvalidOrderException {
        Optional<Order> order = orderRepository.findById(id);
        Order foundOrder = order.orElseThrow(() -> new InvalidOrderException(INVALID_ORDER));
        return convertOrderToOrderResponse(foundOrder);
    }

    private OrderResponse convertOrderToOrderResponse(Order foundOrder) {
        return OrderResponse.builder()
                .senderName(foundOrder.getPickUpLocation().getSenderName())
                .senderPhoneNumber(foundOrder.getPickUpLocation().getSenderPhoneNumber())
                .location(foundOrder.getPickUpLocation().getCurrentLocation().getCity())
                .carrierName("")
                .destination(foundOrder.getReceiverDestination().getReceiverDestination().getCity())
                .itemType(foundOrder.getType().toString())
                .destination(foundOrder.getReceiverDestination().getReceiverDestination().getCity())
                .location(foundOrder.getPickUpLocation().getCurrentLocation().getCity())
                .orderId(foundOrder.getId())
                .build();
    }

    private static LocalTime timeConverter(SaveOrderRequest saveOrderRequest) {
        String timeString = saveOrderRequest.getDueTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:a");
        LocalTime time = LocalTime.parse(timeString, formatter);
        return time;
    }

    private static Date dateConverter(SaveOrderRequest saveOrderRequest) throws ParseException {
        String date = saveOrderRequest.getDueDate();
        String format = "mm/dd/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date newDate = dateFormat.parse(date);
        return newDate;
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
      return   OrderListDtoResponse.builder().senderName(order.getPickUpLocation().getSenderName()).orderId(order.getId()).senderName(
                order.getPickUpLocation().getSenderName()).senderId(order.getSenderId()).
                senderPhoneNumber(order.getPickUpLocation().getSenderPhoneNumber()).
                currentLocationStreetName(order.getPickUpLocation().getCurrentLocation().getStreetName()).
                destinationStreetName(order.getReceiverDestination().getReceiverDestination().getStreetName()).destinationLandmark(order.getReceiverDestination().getReceiverDestination().getLandMark()).build();
    }
}
