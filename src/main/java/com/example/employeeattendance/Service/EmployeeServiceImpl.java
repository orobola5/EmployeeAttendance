package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeDto;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.SignInRequest;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Availability;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Repository.DepartmentRepository;
import com.example.employeeattendance.Model.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
@Slf4j

public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeRepository repository;

   private final DepartmentService departmentService;
    @Override
    public EmployeeCreateResponse addNewEmployee(EmployeeRequest request) {
       Department department = departmentService.findDepartmentByName(request.getDepartment());

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAddress(request.getAddress());
        employee.setGender(request.getGender());
        employee.setDepartment(department);
        employee.setEmail(request.getEmail());
        employee.setPassword(request.getPassword());
        employee.setAvailability(Availability.PRESENCE);
        employee.setLocalDateTime(LocalDateTime.now());
        employee.setSignIn(false);

        Employee savedEmployee = repository.save(employee);

        EmployeeCreateResponse response = new EmployeeCreateResponse();
        response.setMessage("Success");
        response.setEmployee(savedEmployee);
        return response;
    }

    @Override

   public Employee modifyEmployee(long id, UpdateDto updateDto) {
       Employee employeeRepo = repository.findById(id);


        if(updateDto.getFirstName()!= null){
            employeeRepo.setFirstName(updateDto.getFirstName());
        }

        if(updateDto.getLastName()!=null){
            employeeRepo.setLastName(updateDto.getLastName());
        }

        if(updateDto.getDepartment()!=null) {
            employeeRepo.setDepartment(updateDto.getDepartment());
        }

        if(updateDto.getAddress()!=null) {
            employeeRepo.setAddress(updateDto.getAddress());
        }

        if(updateDto.getGender()!=null) {
            employeeRepo.setGender(updateDto.getGender());
        }



        return  repository.save(employeeRepo);
    }

    @Override
    public EmployeeResponseDto findAllEmployee() {
        List<Employee> employees = repository.findAllEmployee();

        List<EmployeeDto> allEmployees = new ArrayList<>();

        for(Employee e : employees){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setFirstName(e.getFirstName());
            employeeDto.setLastName(e.getLastName());
            employeeDto.setGender(e.getGender());
            employeeDto.setAddress(e.getAddress());
            employeeDto.setDepartment(e.getDepartment());
            allEmployees.add(employeeDto);
        }

        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setEmployeeList(allEmployees);
        return  responseDto;
    }



    @Override
    public List<Employee> findEmployeeByDepartment(String department) {
        List<Employee> employees = repository.findAllEmployee();

        List<Employee> foundEmployee = new ArrayList<>();

        for (Employee e : employees) {
            if (Objects.equals(e.getDepartment().getDepartmentName(), department)) {
                foundEmployee.add(e);
            }
        }
        return foundEmployee;
    }

    @Override
    public String signIn(SignInRequest signInRequest) {
        Employee employee = repository.findByEmail(signInRequest.getEmail());
        if (employee == null){
            throw new ResourceNotFoundException("email does not exist");
        }

        if (!Objects.equals(employee.getPassword(), signInRequest.getPassword())){
            throw new ResourceNotFoundException("wrong password");
        }

        employee.setSignIn(true);
        employee.setSignInTime(LocalDateTime.now());
        repository.save(employee);
        return "Successfully signed in";
    }

    @Override
    public String signOut(long id) {
        Employee employee = repository.findById(id);
        employee.setSignIn(false);
        employee.setSignOutTime(LocalDateTime.now());
        repository.save(employee);
        return "Successfully signed out";
    }

    @Override
    public String registerAvailability(long id, AvailabilityDto availability) {
        Employee employee = repository.findById(id);
        employee.setAvailability(availability.getAvailability());
        repository.save(employee);
        return "OK";
    }

    @Override
    public String findByDate(String date, long id) {
        Employee employee = repository.findById(id);

         DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        if(employee.getSignInTime()== dateTime){
            return employee.getAvailability().name() + " "+ employee.getLocalDateTime().toString();

        }
        else throw new ResourceNotFoundException("Employee was not available at that period");
    }

}
