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


    @Test
    @Rollback(false)
    public void EmailTest() {

        UserForm userForm = new UserForm("Eduardo", "du-eduardo10@hotmail.com","45984152806" );
        User userFinal = userRepository.save(User.from(userForm));

        UserForm userForm1 = new UserForm("Eduardo", "du-eduardo10@hotmail.com","45984152806" );

        if(userRepository.findByEmail(userForm1.getEmail()).isPresent()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email ja existe");
        }

        User userFinal1 = userRepository.save(User.from(userForm1));

        assertEquals(userFinal1.getEmail(), userForm1.getEmail());


    }

    @Test
    @Rollback(false)
    public void TelephoneTest() {

        UserForm userForm2 = new UserForm("Eduardo", "du-eduardo10@hotmail.com","45984152806" );
        User userFinal2 = userRepository.save(User.from(userForm2));

        UserForm userForm3 = new UserForm("Eduardo", "edualklkl@hotmail.com","45984155487" );

        if(userRepository.findByTelephone(userForm3.getTelephone()).isPresent()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"telephone ja existe");
        }

        User userFinal3 = userRepository.save(User.from(userForm3));

        assertEquals(userFinal3.getEmail(), userForm3.getEmail());


    }



}
