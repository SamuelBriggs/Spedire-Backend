package com.spedire.Spedire;

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

        }
        @Test
        public void testThatUserCanRegister() throws SpedireException {
            RegistrationRequest request = new RegistrationRequest();
            request.setEmail("spediretech@gmail.com");
            request.setFirstName("Michael");
            request.setLastName("Josh");
            request.setPassword("Mich1234!");

            var response = userService
                    .register("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg3NTM1NjksImV4cCI6MTY4ODg3MzU2OSwicGhvbmVOdW1iZXIiOiIwODEzODczMjUwMyJ9.DdXFPI9-jIadGKCuXUDEYocyD5NLRgfUW59tRI4RAwhosRfeva1py8dmhDzZyt_dhPmzZLWuUcv_7JP2rXxC2w", request);
            System.out.println(response.toString());
            assertThat(response).isNotNull();
        }
}
