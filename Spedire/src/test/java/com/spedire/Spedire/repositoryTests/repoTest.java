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
        Role role2 = Role.SENDER;

        User user = User.builder().phoneNumber("09051243133").roles(Set.of(role)).build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }

    @Test
    public void test_that_repoCanFindById(){

        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        Optional<User> userByPhone = userRepository.findUserByPhoneNumber("090");
        System.out.println(userByPhone.get().getFirstName());



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
