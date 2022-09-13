package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.EmployeeResponse;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Repository.DepartmentRepository;
import com.example.employeeattendance.Model.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeRepository repository;
   private final DepartmentRepository departmentRepository;
    @Override
    public EmployeeResponse addNewEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAddress(request.getAddress());
        employee.setGender(request.getGender());
        employee.setDepartment(request.getDepartment());
        departmentRepository.save(employee.getDepartment());

        Employee savedEmployee = repository.save(employee);
        EmployeeResponse response = new EmployeeResponse();
       response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setGender(savedEmployee.getGender());
        response.setAddress(savedEmployee.getAddress());
        response.setDepartment(savedEmployee.getDepartment());
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
    public List<Employee> findAllEmployee() {

        return repository.findAll();

    }

    @Override
    public List<Department> findAllDepartment() {

        return departmentRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeByDepartment(Department department) {
        log.info("###### FIND BY DEPARTMENT {}",department);
        Optional<Employee> employees= repository.findEmployeeByDepartment(department);
        if (employees.isEmpty()){
            throw  new ResourceNotFoundException("employee is not in this department");
        }

       return employees;

    }
}
