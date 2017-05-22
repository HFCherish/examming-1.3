package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.employees.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    Employee ofId(@Param("id") long id);

    void save(@Param("employee") Employee employee);

    List<Employee> findAll();

    void delete(@Param("id") long id);

    void update(@Param("id") long id, @Param("employee") Employee employee);
}
