//package com.spedire.Spedire.repositoryTests;
//
//import com.spedire.Spedire.models.Address;
//import com.spedire.Spedire.models.Reciever;
//import com.spedire.Spedire.models.Order;
//import com.spedire.Spedire.models.PickUp;
//import com.spedire.Spedire.repositories.OrderRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@Slf4j
//public class orderTest {
//    @Autowired
//    private OrderRepository orderRepository;
//    @Test
//    void testThatThreeOrdersCanBeSaved(){
//        Address address_a = new Address();
//        address_a.setCity("Yaba");
//        address_a.setStreetName("Herbert Macaulay");
//        Reciever destination = new Reciever();
//        destination.setReceiverName("Joshua");
//        destination.getReceiverDestination(address_a);
//
//        Address address_b = new Address();
//        address_b.setCity("Ikeja");
//        address_b.setStreetName("Computer Village");
//        PickUp pickUp = new PickUp();
//        pickUp.setCurrentLocation(address_b);
//        pickUp.setSenderName("Samuel");
//        pickUp.setPhoneNumber("09051243133");
//
//        Order order = new Order();
//        order.setPickUpLocation(pickUp);
//        order.setReceiverDestination(destination);
//        order.setCreatedAt(LocalDateTime.now().plusDays(70L));
//        order.setDescription("Clothing");
//
//
//        Address address1_a = new Address();
//        address1_a.setCity("Yaba");
//        address1_a.setStreetName("Herbert Macaulay");
//        Reciever destination1 = new Reciever();
//        destination1.setReceiverName("Renike");
//        destination1.setReceiverLocation(address1_a);
//
//        Address address1_b = new Address();
//        address1_b.setCity("Yaba");
//        address1_b.setStreetName("Onike");
//        PickUp pickUp1 = new PickUp();
//        pickUp1.setCurrentLocation(address_b);
//        pickUp1.setSenderName("chidi");
//        pickUp1.setPhoneNumber("09051243133");
//
//        Order order1 = new Order();
//        order1.setPickUpLocation(pickUp1);
//        order1.setReceiverDestination(destination1);
//        order1.setCreatedAt(LocalDateTime.now().plusDays(500L));
//        order1.setDescription("Document");
//
//        Address address2_a = new Address();
//        address2_a.setCity("VI");
//        address2_a.setStreetName("Amodu Tijani");
//        Reciever destination2 = new Reciever();
//        destination2.setReceiverName("Folahan");
//        destination2.setReceiverLocation(address2_a);
//
//        Address address2_b = new Address();
//        address2_b.setCity("Berger");
//        address2_b.setStreetName("Kosoko street");
//        PickUp pickUp2 = new PickUp();
//        pickUp2.setCurrentLocation(address2_b);
//        pickUp2.setSenderName("Zainab");
//        pickUp2.setPhoneNumber("09051243133");
//
//        Order order2 = new Order();
//        order2.setPickUpLocation(pickUp2);
//        order2.setReceiverDestination(destination2);
//        order2.setCreatedAt(LocalDateTime.now().plusDays(2L));
//        order2.setDescription("Phone Accessories");
//        order2.setCarrierId("64a96d4af418912106950d5e");
//        order2.setSenderId("64a97266e3c927690b31867d");
//
//      //   orderRepository.save(order);
//     //   orderRepository.save(order1);
//       orderRepository.save(order2);
//    }
//
//
//}
