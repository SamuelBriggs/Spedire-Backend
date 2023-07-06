package com.spedire.Spedire.repositoryTests;


import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;


@DataMongoTest
public class repoTest {

    @Autowired
    private UserRepository userRepository;

    @Test

    public void test_That_repoCanSave(){


        Role role = Role.ROLE_USER;
        Role role2 = Role.ROLE_ADMIN;

        User user = User.builder().firstName("Sam").lastName("Tolu").
                email("tolalwode@gmail.com").
                phoneNumber("0905124").password("1234").roles(Set.of(role2)).build();

        User user2 = User.builder().firstName("Tinuade").lastName("Esther").email("tinuade@gmail.com").
                build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }
    @Test
    public void test_that_repoCanFindById(){

        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        User user1 = userRepository.findByPhoneNumber("0905124");
        Assertions.assertThat(user).isNotNull();
    }



}
