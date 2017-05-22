package com.thoughtworks.ketsu.domain.departments;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface DepartmentRepo {
    Optional<Department> ofId(long id);

    Department save(Department department);

    List<Department> findAll();

    void delete(long id);

    void update(long id, Department department);
}
