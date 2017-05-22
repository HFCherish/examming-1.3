package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.DepartmentMapper;

import javax.inject.Inject;
import java.util.List;
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

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public void delete(long id) {
        departmentMapper.delete(id);
    }

    @Override
    public void update(long id, Department department) {
        departmentMapper.update(id, department);
    }
}
