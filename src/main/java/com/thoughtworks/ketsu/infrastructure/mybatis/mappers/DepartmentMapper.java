package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.departments.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    Department ofId(@Param("id") long id);

    void save(@Param("department") Department department);

}
