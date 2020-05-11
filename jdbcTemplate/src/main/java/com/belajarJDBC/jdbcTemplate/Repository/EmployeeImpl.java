package com.belajarJDBC.jdbcTemplate.Repository;

import com.belajarJDBC.jdbcTemplate.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("EmployeeRespository")
public class EmployeeImpl implements EmployeeRepository{

    // nanti dia kana langsung impl
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from employee",Integer.class);
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate
                .update(
                        "INSERT INTO public.employee(lastname,firstname,address,city) values(?,?,?,?)",
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getAddress(),
                        employee.getCity()
                );
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update(
                "update employee set lastname=?, firstname=?, address=?, city=? where employeeid=?",
                    employee.getLastName(),
                    employee.getFirstName(),
                    employee.getAddress(),
                    employee.getCity(),
                    employee.getEmployeeId()
        );
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from employee where employeeid=?",id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from employee",
                (rs,rowNum) -> new Employee(
                        rs.getLong("employeeid"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("address"),
                        rs.getString("city")
                )
        );
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }
}
