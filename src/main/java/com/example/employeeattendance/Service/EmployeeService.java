package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.EmployeeResponse;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponse addNewEmployee(EmployeeRequest request );

    @Transient
    Employee modifyEmployee(Long id, UpdateDto updateDto);

    List<Employee>findAllEmployee();

    List<Department> findAllDepartment();

    Optional<Employee> findEmployeeByDepartment(Department department);





}
