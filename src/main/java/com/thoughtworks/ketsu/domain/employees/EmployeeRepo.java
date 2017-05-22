package com.thoughtworks.ketsu.domain.employees;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface EmployeeRepo {
    Optional<Employee> ofId(long id);

    Employee save(Employee employee);

    List<Employee> findAll();

    void delete(long id);

    void update(long id, Employee employee);
}
