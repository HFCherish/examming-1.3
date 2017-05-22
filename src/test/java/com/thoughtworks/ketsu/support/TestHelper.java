package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;

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

}
