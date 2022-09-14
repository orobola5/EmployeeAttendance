package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeDto;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.SignInRequest;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Enum.Availability;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Data.Enum.Gender;
import com.example.employeeattendance.Model.Repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class EmployeeServiceTest {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;



    @BeforeEach
    void setup(){
        Department department1 = new Department();
        department1.setDepartmentName("Admin");
        Department department2 = new Department();
        department2.setDepartmentName("Marketing");
        Department department3 = new Department();
        department3.setDepartmentName("Engineering");

//        departmentRepository.saveAll(List.of(department1,department2,department3));

        departmentService.save(department1);
        departmentService.save(department2);
        departmentService.save(department3);

        EmployeeRequest request= new EmployeeRequest();
        request.setFirstName("pepe");
        request.setLastName("Ola");
        request.setGender(Gender.FEMALE);
        request.setAddress("3,Chris Edward,Old Oko-Oba,Lagos.");
        request.setPassword("1567");
        request.setEmail("peace@yahoo.com");
        request.setDepartment("Admin");

        EmployeeRequest request2= new EmployeeRequest();
        request2.setFirstName("Jojo");
        request2.setLastName("Excel");
        request2.setGender(Gender.MALE);
        request2.setAddress("AXYZ");
        request2.setPassword("1212");
        request2.setEmail("excel@yahoo.com");
        request2.setDepartment("Engineering");

        EmployeeRequest request3= new EmployeeRequest();
        request3.setFirstName("Dove");
        request3.setLastName("Queen");
        request3.setGender(Gender.FEMALE);
        request3.setAddress("Yaba,Lagos");
        request3.setPassword("123444");
        request3.setEmail("dove@yahoo.com");
        request3.setDepartment("Marketing");


        EmployeeCreateResponse response= employeeService.addNewEmployee(request);
        EmployeeCreateResponse response2= employeeService.addNewEmployee(request2);
        EmployeeCreateResponse response3= employeeService.addNewEmployee(request3);

    }

//    @AfterEach
//    void tearDown(){
//        employeeService.deleteAllEmployee();
//    }
    @Test
    @DisplayName("test  to add a new employee")
    void addNewEmployee() {
        EmployeeRequest request= new EmployeeRequest();
        request.setFirstName("ope");
        request.setLastName("Ola");
        request.setGender(Gender.FEMALE);
        request.setAddress("3,Chris Edward,Old Oko-Oba,Abule-Egba,Lagos.");
        request.setPassword("1234");
        request.setEmail("tepe@yahoo.com");
        request.setDepartment("Admin");
        EmployeeCreateResponse response= employeeService.addNewEmployee(request);
        assertNotNull(response);
    }

    @Test
    @DisplayName("test to find all department in the organisation")
    void testToFindAllDepartmentInTheOrganisation() {
        DepartmentResponse departmentList = departmentService.findAllDepartment();
        assertNotNull(departmentList);
        assertEquals(3, departmentList.getDepartments().size());
    }

    @Test
    @DisplayName("test to find all employee in the organisation")
    void testToFindAllEmployeeInTheOrganisation() {
        EmployeeResponseDto employeeListList = employeeService.findAllEmployee();
        assertNotNull(employeeListList);
//        System.out.println(employeeListList);
    }


    @Test
    @DisplayName("test to find each employee in the organisation by their department")
    void testToFindEmployeeInTheOrganisationByDepartment()throws ResourceNotFoundException {
        List<Employee> employee=employeeService.findEmployeeByDepartment("Admin");
        assertNotNull(employee);
        assertEquals(1,employee.size());
    }

    @Test
    @DisplayName("test to sign-in")
    void testThatUserCanSignIn(){
        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setEmail("peace@yahoo.com");
        signInRequest.setPassword("1567");
        String message = employeeService.signIn(signInRequest);
        assertEquals("Successfully signed in",message);
        System.out.println(message);
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

        AvailabilityDto availabilityDto2 = new AvailabilityDto();
        availabilityDto2.setAvailability(Availability.SICK_LEAVE);
        String message2 = employeeService.registerAvailability(2L,availabilityDto2);
        assertEquals("OK",message2);
    }
    @Test
    @DisplayName("test to modify existing employee")
    void testThatEmployeeDetailCanBeModify() {
        UpdateDto updateDto=new UpdateDto();
        updateDto.setFirstName("Lauretta");
        Department department2 = departmentService.findByName("Marketing");
        updateDto.setDepartment(department2);
        Employee employee=employeeService.modifyEmployee(1l,updateDto);
        assertEquals("Lauretta",employee.getFirstName());
        assertEquals("Marketing",employee.getDepartment().getDepartmentName());

    }

    @Test
    public void testToFindEmployeeByDateRange(){

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from =LocalDate.parse("2022-09-14", dateTimeFormatter);
        LocalDate to =LocalDate.parse("2022-09-14", dateTimeFormatter);
        List<EmployeeDto> employeeList = employeeService.findByDateRange(from, to);
        assertEquals(2, employeeList.size());

    }


}