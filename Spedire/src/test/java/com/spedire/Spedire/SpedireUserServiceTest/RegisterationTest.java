//package com.spedire.Spedire.SpedireUserServiceTest;
//
//import com.spedire.Spedire.dtos.request.RegistrationRequest;
//import com.spedire.Spedire.dtos.response.RegistrationResponse;
//import com.spedire.Spedire.exceptions.SpedireException;
//import com.spedire.Spedire.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class RegisterationTest {
//        @Autowired
//        private UserService userService;
//
//    private RegistrationResponse response;
//
//        @BeforeEach
//        public void setUp() throws SpedireException {
//
//        }
//        @Test
//        public void testThatUserCanRegister() throws SpedireException {
//            RegistrationRequest request = new RegistrationRequest();
//            request.setEmail("mordoruyde@gufum.com");
//            request.setFirstName("kor");
//            request.setLastName("idan");
//            request.setPassword("Mich1234!");
//<<<<<<< HEAD:Spedire/src/test/java/com/spedire/Spedire/SpedireUserServiceTest/RegisterationTest.java
//            response = userService.register(request);
//        }
//
//        @Test
//        public void testThatUserCanRegister() {
//=======
//
//            var response = userService
//                    .register("Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg4NDkyMzYsImV4cCI6MTY4ODk2OTIzNiwicGhvbmVOdW1iZXIiOiIwNzA2OTMxMDAwNiIsIlJvbGVzIjp7InJvbGUiOiJBRE1JTiJ9fQ.s5zHM924B5wqtrl2m9HoOGHEIVWDDm1mzk4rsv6W5YoK-U_yjeFJh0d6EeQAvWkPScUJQGcqWrAAwXEUiR0s0Q", request);
//            System.out.println(response.toString());
//>>>>>>> ec4de2c98445776248af365f1a3a381f4cbdcf7d:Spedire/src/test/java/com/spedire/Spedire/RegisterationTest.java
//            assertThat(response).isNotNull();
//        }
//}
