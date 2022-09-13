package com.example.employeeattendance.Dto.Response;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Data.Gender;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseDto {
   private List<EmployeeDto> employeeList;
}
