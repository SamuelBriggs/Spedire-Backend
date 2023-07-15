package com.spedire.Spedire.SpedireUserServiceTest;

import com.spedire.Spedire.dtos.request.SaveOrderRequest;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.OrderService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static com.spedire.Spedire.utils.AppUtils.ORDER_PROCESSED;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    private SaveOrderRequest saveOrderRequest = new SaveOrderRequest();
    private String senderId = "64b162d04077f72cd44bdc7f";

    @BeforeEach
    void setUp() {
        saveOrderRequest.setSenderName("Michael Ike Joshua");
        saveOrderRequest.setPhoneNumber("07069310006");
        saveOrderRequest.setDescription("I am sending 5 pairs of shoe and 100 pieces of necklace");
        saveOrderRequest.setImage("Michael Image");
        saveOrderRequest.setDueTime("09:00:PM");
        saveOrderRequest.setReceiverName("Prince of Dubai");
        saveOrderRequest.setItemType("FASHION");
        saveOrderRequest.setCostOfItem("50000");
        saveOrderRequest.setWeight("50kg");
        saveOrderRequest.setPickUpCity("Yaba");
        saveOrderRequest.setReceiverBusStop("Amuwo Odofin Busstop");
        saveOrderRequest.setPickUpLandmark("Big Iroko Tree");
        saveOrderRequest.setReceiverStreetName("Adepanla");
        saveOrderRequest.setReceiverHouseNumber("68");
        saveOrderRequest.setPickUpStreetName("Ifesowapo Julius");
        saveOrderRequest.setDueDate("07/16/2023");
        saveOrderRequest.setReceiverDestination("VI");
        saveOrderRequest.setPickBusStop("Eko Hotel");
        saveOrderRequest.setPickHouseNumber("87");
        saveOrderRequest.setPickUpStreetName("John Jones");
    }


    @Test
    void orderCanBeSavedTest() throws SpedireException, ParseException {
        var response = orderService.saveOrder(saveOrderRequest, senderId);
        assertThat(response.getMessage()).isEqualTo(ORDER_PROCESSED);
    }

}
