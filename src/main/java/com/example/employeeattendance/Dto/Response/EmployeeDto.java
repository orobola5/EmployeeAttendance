package com.example.employeeattendance.Dto.Response;


import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Data.Enum.Gender;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private Department department;




}
