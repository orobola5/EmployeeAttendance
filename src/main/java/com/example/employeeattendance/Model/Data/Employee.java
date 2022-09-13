package com.example.employeeattendance.Model.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
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

    private Boolean signIn;

    private Availability availability;

}