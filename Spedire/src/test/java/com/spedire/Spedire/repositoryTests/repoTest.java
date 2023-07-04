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

        User user = User.builder().firstName("Tinuade").lastName("Esther").email("tinuade@gmail.com").
                build();
        var name = userRepository.save(user);
        Assertions.assertThat(name).isNotNull();
    }
    @Test
    public void test_that_repoCanFindById(){
        Optional<User> user = userRepository.findById("64a2b517e86b786d6be94a50");
        System.out.println(user.get().getFirstName());
        Assertions.assertThat(user).isNotNull();
    }



}
