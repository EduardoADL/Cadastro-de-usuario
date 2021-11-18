package com.example.prova;


import com.example.prova.User.User;
import com.example.prova.User.UserForm;
import com.example.prova.User.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntergrationUser {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void incia() throws Exception {
        UserForm userForm = new UserForm("Eduardo","du-eduardo10@hotmail.com","984152806");

        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userForm)))
                .andReturn();

    }

    @Test
    public void verificaSeAFoiCriado() throws Exception{
        UserForm userForm = new UserForm("Eduardo","du-eduardo214@hotmail.com","984152895");


        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userForm)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void verificaSeEmailDuplicado() throws Exception{
        UserForm userForm = new UserForm("Eduardo","du-eduardo10@hotmail.com","984152806");

        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userForm)))
                .andExpect(status().isInternalServerError())
                .andReturn();

    }


}
