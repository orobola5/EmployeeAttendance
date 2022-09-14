package com.example.employeeattendance.Dto.Request;

import com.example.employeeattendance.Model.Data.Enum.Gender;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private String department;
    private String email;
    private String password;


}
