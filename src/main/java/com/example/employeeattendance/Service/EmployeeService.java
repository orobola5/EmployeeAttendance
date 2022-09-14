package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.SignInRequest;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Model.Data.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;
@Service
public interface EmployeeService {
    EmployeeCreateResponse addNewEmployee(EmployeeRequest request );

    Employee modifyEmployee(long id, UpdateDto updateDto);

    EmployeeResponseDto findAllEmployee();


    List<Employee> findEmployeeByDepartment(String department);


    String signIn(SignInRequest signInRequest);

    String signOut(long id);

    String registerAvailability(long id, AvailabilityDto availability);

    void deleteAllEmployee();
}
