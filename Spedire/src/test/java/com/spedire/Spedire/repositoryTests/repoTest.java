package com.spedire.Spedire.repositoryTests;


import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;


@SpringBootTest
@Slf4j
public class repoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private PasswordEncoder passwordEncoder;

    @Test

    public void test_That_repoCanSave(){


        Role role = Role.USER;
        Role role2 = Role.ADMIN;
        User user = User.builder().firstName("Sam").lastName("Tolu").
                email("to@gmail.com").
                phoneNumber("090").password(passwordEncoder.encode("1234")).roles(Set.of(role2)).build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }
    @Test
    public void test_that_repoCanFindById(){

        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        Optional<User> userByPhone = userRepository.findUserByPhoneNumber("090");
        System.out.println(userByPhone.get().getFirstName());


        Assertions.assertThat(user).isNotNull();
    }



}
