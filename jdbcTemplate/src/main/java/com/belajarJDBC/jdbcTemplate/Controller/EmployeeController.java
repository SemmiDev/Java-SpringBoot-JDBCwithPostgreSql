package com.belajarJDBC.jdbcTemplate.Controller;

import com.belajarJDBC.jdbcTemplate.Entity.Employee;
import com.belajarJDBC.jdbcTemplate.Repository.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    @Qualifier("EmployeeRespository") // sesuai dengan nama @repository di impl
    private EmployeeImpl employeeRepository;

    @GetMapping("/employee")
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public int PostEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/employee")
    public int deleteEmployee(@RequestParam("id") Long id){
        return employeeRepository.deleteById(id);
    }

    @PutMapping("/employee")
    public int updateEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.update(employee);
    }
}
