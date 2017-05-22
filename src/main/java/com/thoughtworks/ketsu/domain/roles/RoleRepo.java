package com.thoughtworks.ketsu.domain.roles;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface RoleRepo {
    Optional<Role> ofId(long id);

    Role save(Role role);

    List<Role> findAll();

    void delete(long id);

    void update(long id, Role role);
}
