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

<<<<<<< HEAD

        Role role = Role.NEW_USER;
        Role role2 = Role.ADMIN;

        User user = User.builder().firstName("Sam").lastName("Tolu").
                email("tolalwode@gmail.com").
                phoneNumber("0905124").password("1234").roles(Set.of(role2)).build();

        User user2 = User.builder().firstName("Tinuade").lastName("Esther").email("tinuade@gmail.com").
=======
        User user = User.builder()
                .firstName("Chibuzo")
                .lastName("Ekenne")
                .email("chibuzo@gmail.com").
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
                build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
        System.out.println("ID -> " + user.getId());
    }
    @Test
    public void test_that_repoCanFindById(){
<<<<<<< HEAD

<<<<<<< HEAD
        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        User user1 = userRepository.findByPhoneNumber("0905124");
=======
        Optional<User> user = userRepository.findById("64a3090f11746d4febf06db6");
        System.out.println(user.get().getFirstName());
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
=======
        Optional<User> user = userRepository.findById("64a741c8d91461440b1b5d7a");
        System.out.println(user);
>>>>>>> 4c179b2c0f4695b92f531e636253a866c3da9bc3
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
