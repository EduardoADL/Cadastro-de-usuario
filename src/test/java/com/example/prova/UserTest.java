package com.example.prova;

import com.example.prova.User.User;
import com.example.prova.User.UserForm;
import com.example.prova.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(false)
    public void UserCreated() {
        UserForm userFormCriado = new UserForm("Alan","Alandasilda@gmail.com","4578961345");

        User userFinalCriado = userRepository.save(User.from(userFormCriado));

        assertEquals(userFinalCriado.getName(), userFormCriado.getName());
        assertEquals(userFinalCriado.getTelephone(), userFormCriado.getTelephone());
        assertEquals(userFinalCriado.getEmail(), userFormCriado.getEmail());
    }




}
