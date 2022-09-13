package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeDto;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
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

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeRepository repository;
   private final DepartmentRepository departmentRepository;
    @Override
    public EmployeeCreateResponse addNewEmployee(EmployeeRequest request) {
        Department department = departmentRepository.findByName(request.getDepartment());
        if(department==null){
            throw new ResourceNotFoundException("Department does not exist");
        }

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAddress(request.getAddress());
        employee.setGender(request.getGender());
        employee.setDepartment(department);
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
    @Transient
    public Employee modifyEmployee(Long id, UpdateDto updateDto) {
        Optional<Employee> employeeRepo = repository.findById(id);
        Employee employee = employeeRepo.get();

        if(updateDto.getFirstName()!= null){
            employee.setFirstName(updateDto.getFirstName());
        }
        if(updateDto.getLastName()!=null){
            employee.setLastName(updateDto.getLastName());

        }
        if(updateDto.getDepartment()!=null) {
            employee.setDepartment(updateDto.getDepartment());
        }
        if(updateDto.getAddress()!=null) {
            employee.setAddress(updateDto.getAddress());
        }
        return  repository.save(employee);
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
    public DepartmentResponse findAllDepartment() {
        List<Department> departments = departmentRepository.findAllDepartments();
        DepartmentResponse response = new DepartmentResponse();
        response.setDepartments(departments);
       return  response;
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
    public String signIn(long id) {
        Employee employee = repository.findById(id);
        employee.setSignIn(true);
        repository.save(employee);
        return "Successfully signed in";
    }

    @Override
    public String signOut(long id) {
        Employee employee = repository.findById(id);
        employee.setSignIn(false);
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
}
