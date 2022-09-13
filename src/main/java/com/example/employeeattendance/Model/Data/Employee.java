package com.example.employeeattendance.Model.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "address")
    private String address;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "date")
    private LocalDateTime localDateTime;

    @Column(name="sign_in_time")
    private LocalDateTime signInTime;

    @Column(name="sign_out_time")
    private LocalDateTime signOutTime;

    private Boolean signIn;

    private Availability availability;

}