package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Availability;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Data.Gender;
import com.example.employeeattendance.Model.Repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class EmployeeServiceTest {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    void setup(){
        Department department1 = new Department();
        department1.setDepartmentName("Admin");
        Department department2 = new Department();
        department2.setDepartmentName("Marketing");
        Department department3 = new Department();
        department3.setDepartmentName("Engineering");

        departmentRepository.saveAll(List.of(department1,department2,department3));


        EmployeeRequest request= new EmployeeRequest();
        request.setFirstName("peace");
        request.setLastName("Olanrewaju");
        request.setGender(Gender.FEMALE);
        request.setAddress("3,Chris Edward,Old Oko-Oba,Abule-Egba,Lagos.");
        request.setPassword("1234");
        request.setEmail("peace@yahoo.com");
        request.setDepartment("Admin");

        EmployeeRequest request2= new EmployeeRequest();
        request2.setFirstName("John");
        request2.setLastName("Excel");
        request2.setGender(Gender.MALE);
        request2.setAddress("XYZ");
        request2.setPassword("1234");
        request2.setEmail("excel@yahoo.com");
        request2.setDepartment("Engineering");

        EmployeeRequest request3= new EmployeeRequest();
        request3.setFirstName("love");
        request3.setLastName("queen");
        request3.setGender(Gender.FEMALE);
        request3.setAddress("Yaba, Sabo");
        request3.setPassword("123444");
        request3.setEmail("love@yahoo.com");
        request3.setDepartment("Admin");


        EmployeeCreateResponse response= employeeService.addNewEmployee(request);
        EmployeeCreateResponse response2= employeeService.addNewEmployee(request2);
        EmployeeCreateResponse response3= employeeService.addNewEmployee(request3);



    }

    @Test
    @DisplayName("test  to add a new employee")
    void addNewEmployee() {
        EmployeeRequest request= new EmployeeRequest();
        request.setFirstName("peace");
        request.setLastName("Olanrewaju");
        request.setGender(Gender.FEMALE);
        request.setAddress("3,Chris Edward,Old Oko-Oba,Abule-Egba,Lagos.");
        request.setPassword("1234");
        request.setEmail("peace@yahoo.com");
        request.setDepartment("Admin");
        EmployeeCreateResponse response= employeeService.addNewEmployee(request);
        assertNotNull(response);
    }


    @Test
    @DisplayName("test to find all department in the organisation")
    void testToFindAllDepartmentInTheOrganisation() {
        DepartmentResponse departmentList = employeeService.findAllDepartment();
        assertNotNull(departmentList);
        assertEquals(3, departmentList.getDepartments().size());
    }

    @Test
    @DisplayName("test to find all employee in the organisation")
    void testToFindAllEmployeeInTheOrganisation() {
        EmployeeResponseDto employeeListList = employeeService.findAllEmployee();
        assertNotNull(employeeListList);
        System.out.println(employeeListList);
    }


    @Test
    @DisplayName("test to find each employee in the organisation by their department")
    void testToFindEmployeeInTheOrganisationByDepartment()throws ResourceNotFoundException {
        List<Employee> employee=employeeService.findEmployeeByDepartment("Admin");
        assertNotNull(employee);
        assertEquals(2,employee.size());
        System.out.println(employee);
    }

    @Test
    @DisplayName("test to sign-in")
    void testThatUserCanSignIn(){
        String message = employeeService.signIn(1L);
        assertEquals("Successfully signed in",message);
    }

    @Test
    @DisplayName("test to sign-out")
    void testThatUserCanSignOut(){
        String message = employeeService.signOut(1L);
        assertEquals("Successfully signed out",message);
    }

    @Test
    @DisplayName("test for sick leave")
    void testThatUserCanRegisterForAvailabilty(){
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setAvailability(Availability.ABSENCE);
        String message = employeeService.registerAvailability(1L,availabilityDto);
        assertEquals("OK",message);
    };


}