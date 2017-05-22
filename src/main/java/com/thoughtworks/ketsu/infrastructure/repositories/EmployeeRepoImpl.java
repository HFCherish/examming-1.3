package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.EmployeeMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class EmployeeRepoImpl implements EmployeeRepo {
    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public Optional<Employee> ofId(long id) {
        return Optional.ofNullable(employeeMapper.ofId(id));
    }

    @Override
    public Employee save(Employee employee) {
        employeeMapper.save(employee);
        return employeeMapper.ofId(employee.getId());
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public void delete(long id) {
        employeeMapper.delete(id);
    }

    @Override
    public void update(long id, Employee employee) {
        employeeMapper.update(id, employee);
    }
}
