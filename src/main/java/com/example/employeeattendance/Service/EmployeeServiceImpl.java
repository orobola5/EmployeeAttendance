package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Request.AvailabilityDto;
import com.example.employeeattendance.Dto.Request.EmployeeRequest;
import com.example.employeeattendance.Dto.Response.EmployeeCreateResponse;
import com.example.employeeattendance.Dto.Response.EmployeeDto;
import com.example.employeeattendance.Dto.Response.EmployeeResponseDto;
import com.example.employeeattendance.Dto.SignInRequest;
import com.example.employeeattendance.Dto.UpdateDto.UpdateDto;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Attendance;
import com.example.employeeattendance.Model.Data.Enum.Availability;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import com.example.employeeattendance.Model.Repository.AttendanceRepository;
import com.example.employeeattendance.Model.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
@Slf4j

public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeRepository repository;

   private final DepartmentService departmentService;

   private final AttendanceRepository attendanceRepository;


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
        employee.setLocalDateTime(LocalDateTime.now());
        Attendance attendance=new Attendance();
        attendance.setAvailability(Availability.PRESENCE);
        attendance.setSignIn(false);

      Attendance register=  attendanceRepository.save(attendance);
        employee.setAttendance(register);

        Employee savedEmployee = repository.save(employee);
        EmployeeCreateResponse response = new EmployeeCreateResponse();
        response.setMessage("Success");
        response.setEmployee(savedEmployee);
        return response;
    }
@Override
    public Employee modifyEmployee(long id, UpdateDto updateDto) {

        Employee employeeRepo = repository.findById(id);


        if (updateDto.getFirstName() != null) {
            employeeRepo.setFirstName(updateDto.getFirstName());
        }

        if (updateDto.getLastName() != null) {
            employeeRepo.setLastName(updateDto.getLastName());
        }

        if (updateDto.getDepartment() != null) {
            employeeRepo.setDepartment(updateDto.getDepartment());
        }

        if (updateDto.getAddress() != null) {
            employeeRepo.setAddress(updateDto.getAddress());
        }

        if (updateDto.getGender() != null) {
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

        Attendance attendance=new Attendance();
        attendance.setSignIn(true);
        attendance.setSignInTime(LocalDateTime.now());
        Attendance signIn=  attendanceRepository.save(attendance);
        employee.setAttendance(signIn);
        repository.save(employee);
        return "Successfully signed in";
    }

    @Override
    public String signOut(long id) {
        Employee employee = repository.findById(id);
        Attendance attendance=new Attendance();
        attendance.setSignIn(false);
        attendance.setSignOutTime(LocalDateTime.now());
        Attendance signOut=  attendanceRepository.save(attendance);
        employee.setAttendance(signOut);
        repository.save(employee);
        return "Successfully signed out";
    }

    @Override
    public String registerAvailability(long id, AvailabilityDto availability) {
        Employee employee = repository.findById(id);
        Attendance attendance=new Attendance();
        attendance.setAvailability(availability.getAvailability());
        Attendance register=  attendanceRepository.save(attendance);
        employee.setAttendance(register);
        repository.save(employee);
        return "OK";
    }

    @Override
    public List<EmployeeDto> findByDateRange(LocalDate from, LocalDate to) {
        List<Employee> employees =repository.findByDateJoinedBetween(from, to);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee: employees) {
//            employeeDtos.add(EmployeeDto.packo(employee));
        }
        return employeeDtos;
    }

    public String generateEmployeeId() {
        long number = repository.findAll().size() + 1;
        String id = String.format("%04d", number);
        return "ST" + id;

    }


}
