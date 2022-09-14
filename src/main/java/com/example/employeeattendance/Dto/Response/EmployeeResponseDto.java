package com.example.employeeattendance.Dto.Response;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponseDto {
   private List<EmployeeDto> employeeList;
}
