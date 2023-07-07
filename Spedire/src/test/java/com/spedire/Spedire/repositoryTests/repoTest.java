package com.spedire.Spedire.repositoryTests;


import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
<<<<<<< HEAD
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7

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


<<<<<<< HEAD
        Role role = Role.USER;
        Role role2 = Role.ADMIN;
=======
        Role role = Role.NEW_USER;
        Role role2 = Role.ADMIN;

>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7
        User user = User.builder().firstName("Sam").lastName("Tolu").
                email("to@gmail.com").
                phoneNumber("090").password(passwordEncoder.encode("1234")).roles(Set.of(role2)).build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }
    @Test
    public void test_that_repoCanFindById(){

<<<<<<< HEAD
        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        Optional<User> userByPhone = userRepository.findUserByPhoneNumber("090");
        System.out.println(userByPhone.get().getFirstName());


=======
        Optional<User> user = userRepository.findById("64a741c8d91461440b1b5d7a");
        System.out.println(user);
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7
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
