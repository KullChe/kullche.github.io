package com.attech.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotEmpty(message = "*Please provide a user name")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String userName;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String passWord;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty(message = "*Please provide your first name")
    private String firstName;
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
    private String roleName;

}
