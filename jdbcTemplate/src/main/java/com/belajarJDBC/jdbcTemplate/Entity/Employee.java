package com.belajarJDBC.jdbcTemplate.Entity;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Employee {

    private Long employeeId;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
}
