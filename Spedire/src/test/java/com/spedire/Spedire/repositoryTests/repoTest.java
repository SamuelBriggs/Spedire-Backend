package com.spedire.Spedire.repositoryTests;


import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;
import java.util.Set;


@DataMongoTest
public class repoTest {

    @Autowired
    private UserRepository userRepository;

    @Test

    public void test_That_repoCanSave(){


        Role role = Role.NEW_USER;
        Role role2 = Role.ADMIN;

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

        Optional<User> user = userRepository.findById("64a741c8d91461440b1b5d7a");
        System.out.println(user);
        Assertions.assertThat(user).isNotNull();

    }
    @Test
    public void test_that_repoCanFindByPhoneNumber(){
        User user1 = userRepository.findByPhoneNumber("08138732503");
        System.out.println(user1);
        Assertions.assertThat(user1).isNotNull();
    }

    @Test
    public void test_that_repoCanFindByEmail(){
        User user1 = userRepository.findByEmail("spediretech@gmail.com");
        System.out.println(user1);
        Assertions.assertThat(user1).isNotNull();
    }
}
