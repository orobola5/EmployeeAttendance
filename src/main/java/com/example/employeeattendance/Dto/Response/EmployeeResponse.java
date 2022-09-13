package com.example.employeeattendance.Dto.Response;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private Department department;
}
