package com.spedire.Spedire.SpedireUserServiceTest;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RegisterationTest {
        @Autowired
        private UserService userService;

    private RegistrationResponse response;

        @BeforeEach
        public void setUp() throws SpedireException {
            response = new RegistrationResponse();
            RegistrationRequest request = new RegistrationRequest();
            request.setEmail("carzahegni@gufum.com");
            request.setFirstName("car");
            request.setLastName("zahn");
            request.setPassword("Mich1234!");
            response = userService.register(request);
        }
        @Test
        public void testThatUserCanRegister() throws SpedireException {
            assertThat(response).isNotNull();
        }
}
