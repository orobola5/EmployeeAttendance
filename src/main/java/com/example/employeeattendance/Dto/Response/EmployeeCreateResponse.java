package com.example.employeeattendance.Dto.Response;

import com.example.employeeattendance.Model.Data.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeeCreateResponse {
   private String message;
   private Employee employee;
}
