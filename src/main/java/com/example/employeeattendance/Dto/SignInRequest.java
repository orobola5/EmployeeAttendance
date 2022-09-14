package com.example.employeeattendance.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
