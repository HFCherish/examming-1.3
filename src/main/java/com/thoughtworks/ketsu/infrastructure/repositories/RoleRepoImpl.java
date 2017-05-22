package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.RoleMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class RoleRepoImpl implements RoleRepo {
    @Inject
    RoleMapper roleMapper;

    @Override
    public Optional<Role> ofId(long id) {
        return Optional.ofNullable(roleMapper.ofId(id));
    }

    @Override
    public Role save(Role role) {
        roleMapper.save(role);
        return roleMapper.ofId(role.getId());
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void delete(long id) {
        roleMapper.delete(id);
    }

    @Override
    public void update(long id, Role role) {
        roleMapper.update(id, role);
    }
}
