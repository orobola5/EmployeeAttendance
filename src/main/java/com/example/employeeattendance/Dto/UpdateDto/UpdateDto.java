package com.example.employeeattendance.Dto.UpdateDto;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {
    private String firstName;
    private String lastName;
    private String address;
    private Department department;
}
