package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.DepartmentMapper;

import javax.inject.Inject;
import java.util.Optional;

public class DepartmentRepoImpl implements DepartmentRepo {
    @Inject
    DepartmentMapper departmentMapper;

    @Override
    public Optional<Department> ofId(long id) {
        return Optional.ofNullable(departmentMapper.ofId(id));
    }

    @Override
    public Department save(Department department) {
        departmentMapper.save(department);
        return departmentMapper.ofId(department.getId());
    }
}
