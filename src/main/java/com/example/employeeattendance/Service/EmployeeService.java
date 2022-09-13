package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Model.Data.Employee;

import javax.persistence.Transient;
import java.util.List;

public interface EmployeeService {
    EmployeeCreateResponse addNewEmployee(EmployeeRequest request );

    Employee modifyEmployee(long id, UpdateDto updateDto);

    EmployeeResponseDto findAllEmployee();

    DepartmentResponse findAllDepartment();

    List<Employee> findEmployeeByDepartment(String department);


    String signIn(long id);

    String signOut(long id);

    String registerAvailability(long id, AvailabilityDto availability);

    String findByDate(String date, long id);
}
