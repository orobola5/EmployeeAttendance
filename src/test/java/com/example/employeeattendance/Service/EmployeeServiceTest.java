package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.EmployeeResponse;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Data.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("test  to add a new employee")
    void addNewEmployee() {
        EmployeeRequest request= new EmployeeRequest();
        request.setFirstName("peace");
        request.setLastName("Olanrewaju");
        request.setGender(Gender.FEMALE);
        request.setAddress("3,Chris Edward,Old Oko-Oba,Abule-Egba,Lagos.");

        Department department= new Department();
        department.setDepartmentName("Admin");
        request.setDepartment(department);

        EmployeeResponse response= employeeService.addNewEmployee(request);
        assertNotNull(response);
        assertEquals("peace", response.getFirstName());
        assertEquals("Olanrewaju", response.getLastName());
        assertEquals(Gender.FEMALE, response.getGender());
        assertEquals("3,Chris Edward,Old Oko-Oba,Abule-Egba,Lagos.", response.getAddress());
        assertEquals("Admin",response.getDepartment().getDepartmentName());


        EmployeeRequest request2= new EmployeeRequest();
        request2.setFirstName("AKinola");
        request2.setLastName("Akintunde");
        request2.setGender(Gender.MALE);
        request2.setAddress("34,Abule-Egba,Lagos.");

        Department department2= new Department();
        department2.setDepartmentName("Accounting");
        request2.setDepartment(department2);

        EmployeeResponse response2= employeeService.addNewEmployee(request2);
        assertNotNull(response2);
        assertEquals("AKinola", response2.getFirstName());
        assertEquals("Akintunde", response2.getLastName());
        assertEquals(Gender.MALE, response2.getGender());
        assertEquals("34,Abule-Egba,Lagos.", response2.getAddress());
        assertEquals("Accounting",response2.getDepartment().getDepartmentName());




    }

    @Test
    @DisplayName("test  to modify an existing employee")
    void modifyEmployee(){
        UpdateDto updateDto = new UpdateDto();
        updateDto.setFirstName("Lauretta");
        Department department=new Department();
        department.setDepartmentName("IT");
        updateDto.setDepartment(department);
        Employee employee= employeeService.modifyEmployee(1l,updateDto);
        assertNotNull(employee);
        assertEquals("Lauretta",employee.getFirstName());
        assertEquals("IT",employee.getDepartment().getDepartmentName());

    }

    @Test
    @DisplayName("test to find all department in the organisation")
    void testToFindAllDepartmentInTheOrganisation() {
        List<Department> departmentList = employeeService.findAllDepartment();
        assertNotNull(departmentList);
        assertEquals(2, departmentList.size());

    }

    @Test
    @DisplayName("test to find all employee in the organisation")
    void testToFindAllEmployeeInTheOrganisation() {
        List<Employee> employeeListList = employeeService.findAllEmployee();
        assertNotNull(employeeListList);
        assertEquals(2,employeeListList.size());

    }


    @Test
    @DisplayName("test to find each employee in the organisation by their department")
    void testToFindEmployeeInTheOrganisationByDepartment()throws ResourceNotFoundException {
       Department department=new Department();
       department.setDepartmentName("Admin");
        Employee employee=employeeService.findEmployeeByDepartment(department).get();
        assertNotNull(employee);
        assertEquals("Admin",employee.getDepartment().getDepartmentName());
    }








}