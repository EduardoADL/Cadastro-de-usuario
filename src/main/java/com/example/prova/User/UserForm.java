package com.example.prova.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private String name;
    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telephone;


}
