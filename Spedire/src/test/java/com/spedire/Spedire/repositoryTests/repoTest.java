//package com.spedire.Spedire.repositoryTests;
//
//
//import com.spedire.Spedire.models.Role;
//import com.spedire.Spedire.models.User;
//import com.spedire.Spedire.repositories.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import java.util.Optional;
//import java.util.Set;
//
//
//@SpringBootTest
//@Slf4j
//public class repoTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//
//    public void test_That_repoCanSave(){
//        Role role = Role.USER;
//        Role role2 = Role.SENDER;
//
//<<<<<<< HEAD
//
//        Role role = Role.NEW_USER;
//        Role role2 = Role.ADMIN;
//
//        User user = User.builder().firstName("Sam").lastName("Tolu").
//                email("tolalwode@gmail.com").
//                phoneNumber("0905124").password("1234").roles(Set.of(role2)).build();
//
//        User user2 = User.builder().firstName("Tinuade").lastName("Esther").email("tinuade@gmail.com").build();
//        User name = userRepository.save(user);
//=======
//        User user = User.builder().phoneNumber("0908").firstName("Mike").password(passwordEncoder.encode("1234")).roles(Set.of(role)).build();
//        var name = userRepository.save(user);
//>>>>>>> ec4de2c98445776248af365f1a3a381f4cbdcf7d
//        Assertions.assertThat(name).isNotNull();
//        System.out.println("ID -> " + user.getId());
//    }
//
//    @Test
//    public void test_that_repoCanFindById(){
//<<<<<<< HEAD
//        Optional<User> user = userRepository.findById("64a3090f11746d4febf06db6");
//        System.out.println(user.get().getFirstName());
//=======
//
//        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
//        Optional<User> userByPhone = userRepository.findUserByPhoneNumber("090");
//        System.out.println(userByPhone.get().getFirstName());
//
//
//
//        System.out.println(user);
//
//>>>>>>> ec4de2c98445776248af365f1a3a381f4cbdcf7d
//        Assertions.assertThat(user).isNotNull();
//
//    }
//    @Test
//    public void test_that_repoCanFindByPhoneNumber(){
//        User user1 = userRepository.findByPhoneNumber("08138732503");
//        System.out.println(user1);
//        Assertions.assertThat(user1).isNotNull();
//    }
//
//    @Test
//    public void test_that_repoCanFindByEmail(){
//        User user1 = userRepository.findByEmail("spediretech@gmail.com");
//        System.out.println(user1);
//        Assertions.assertThat(user1).isNotNull();
//    }
//}
