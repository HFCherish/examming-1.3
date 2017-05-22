package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.departments.Department;
import com.thoughtworks.ketsu.domain.departments.DepartmentRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;
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


    public static Map<String, Object> employeeJsonForTest(long departmentId, long roleId) {
        return new HashMap<String, Object>() {{
            put("name", "pzzheng");
            put("department_id", departmentId);
            put("role_id", roleId);
            put("gender", Gender.FEMALE.toString());
        }};
    }

    public static Employee employeeWithDefaultInfo(Department department, Role role) {
        return new Employee("pzzheng", department, role, Gender.FEMALE);
    }

    public static Employee prepareEmployeeWithDefaultInfo(EmployeeRepo employeeRepo, Department department, Role role) {
        return employeeRepo.save(employeeWithDefaultInfo(department, role));
    }
}
