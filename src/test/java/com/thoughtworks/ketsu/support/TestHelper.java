package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.roles.Role;
import com.thoughtworks.ketsu.domain.roles.RoleRepo;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static Map<String, Object> departmentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "it");
        }};
    }

    public static Department departmentWithDefaultInfo() {
        return new Department("it");
    }

    public static Department prepareDepartmentWithDefaultInfo(DepartmentRepo departmentRepo) {
        return departmentRepo.save(departmentWithDefaultInfo());
    }


    public static Map<String, Object> roleJsonForTest() {
        return new HashMap<String, Object>() {{
            put("title", "manager");
        }};
    }

    public static Role roleWithDefaultInfo() {
        return new Role("it");
    }

    public static Role prepareRoleWithDefaultInfo(RoleRepo roleRepo) {
        return roleRepo.save(roleWithDefaultInfo());
    }

}
