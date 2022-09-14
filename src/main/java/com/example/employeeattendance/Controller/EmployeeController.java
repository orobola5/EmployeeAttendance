package com.example.employeeattendance.Controller;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.SignInRequest;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Service.DepartmentService;
import com.example.employeeattendance.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService service;


    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeCreateResponse createEmployee(@RequestBody EmployeeRequest request){
        return employeeService.addNewEmployee(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee modifyEmployee(@PathVariable ("id")long id,@RequestBody UpdateDto updateDto){
        return employeeService.modifyEmployee(id, updateDto);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto findAllEmployee() {
        return employeeService.findAllEmployee();

    }

    @GetMapping("/department")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse findAllDepartment() {
        return service.findAllDepartment();
    }

    @GetMapping("/{department}/department")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByDepartment(@PathVariable("department") String department) {
        return  employeeService.findEmployeeByDepartment(department);
    }

    @PostMapping ("/request")
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody SignInRequest request) {
        return employeeService.signIn(request);

    }
    @PostMapping ("/{id}/id/sign-out")
    @ResponseStatus(HttpStatus.OK)
    public String signOut(@PathVariable("id") long id) {
        return employeeService.signOut(id);

    }

    @PostMapping("/{id}/id/register")
    @ResponseStatus(HttpStatus.OK)
    public String registerAvailability(@PathVariable("id") long id, @RequestBody AvailabilityDto availability) {
        return employeeService.registerAvailability(id,availability);
    }

    @GetMapping("/{date}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String findByDate(@PathVariable("date") String date, @PathVariable("id") long id) {
        return employeeService.findByDate(date, id);
    }
}
