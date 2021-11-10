package com.example.prova;

import com.example.prova.User.User;
import com.example.prova.User.UserForm;
import com.example.prova.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void EmailTest() {

        UserForm userForm = new UserForm("Eduardo", "du-eduardo10@hotmail.com","45984152806" );

        User userFinal = userRepository.save(User.from(userForm));

        boolean isExistEmail = userRepository.findByEmail(userFinal.getEmail()).isPresent();

        userRepository.save(User.from(userForm));


        assertFalse(isExistEmail);

    }

}
