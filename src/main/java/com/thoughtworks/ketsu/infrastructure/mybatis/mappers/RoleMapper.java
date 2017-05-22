package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.roles.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    Role ofId(@Param("id") long id);

    void save(@Param("role") Role role);

    List<Role> findAll();

    void delete(@Param("id") long id);

    void update(@Param("id") long id, @Param("role") Role role);
}
