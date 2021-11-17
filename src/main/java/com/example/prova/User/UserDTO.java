package com.example.prova.User;
import org.hibernate.validator.constraints.UniqueElements;
import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telephone;


    public static  UserDTO from(User user){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return  modelMapper.map(user, UserDTO.class);
    }

}
