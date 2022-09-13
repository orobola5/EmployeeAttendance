package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Model.Data.Availability;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeCreateResponse addNewEmployee(EmployeeRequest request );

    @Transient
    Employee modifyEmployee(Long id, UpdateDto updateDto);

    EmployeeResponseDto findAllEmployee();

    DepartmentResponse findAllDepartment();

    List<Employee> findEmployeeByDepartment(String department);


    String signIn(long id);

    String signOut(long id);

    String registerAvailability(long id, AvailabilityDto availability);
}
