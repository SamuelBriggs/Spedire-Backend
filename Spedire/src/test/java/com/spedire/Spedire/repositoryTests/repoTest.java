package com.spedire.Spedire.repositoryTests;


import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@DataMongoTest
public class repoTest {

    @Autowired
    private UserRepository userRepository;

    @Test

    public void test_That_repoCanSave(){

        User user = User.builder().firstName("Michael").lastName("Josh").email("mich@gmail.com").
                build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }
    @Test
    public void test_that_repoCanFindById(){

<<<<<<< HEAD
        Optional<User> user = userRepository.findById("64a1baa379b14e14b76e83a0");
        System.out.println(user.toString());
=======
        Optional<User> user = userRepository.findById("64a1dda56902e05570863a80");
        System.out.println(user.get().getFirstName());
>>>>>>> 5ef0c82bacc420837850269f7c4faa1c752dc3b6
        Assertions.assertThat(user).isNotNull();
    }



}
